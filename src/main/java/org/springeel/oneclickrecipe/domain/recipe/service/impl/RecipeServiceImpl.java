package org.springeel.oneclickrecipe.domain.recipe.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.repository.FollowRepository;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.exception.FoodErrorCode;
import org.springeel.oneclickrecipe.domain.food.exception.NotFoundFoodException;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto.RecipeAllReadResponseDtoBuilder;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto.RecipeReadResponseDtoBuilder;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.mapper.entity.RecipeEntityMapper;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.service.RecipeFoodEntityMapper;
import org.springeel.oneclickrecipe.domain.recipelike.repository.RecipeLikeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity.RecipeProcessEntityMapper;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.s3.S3Provider;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEntityMapper recipeEntityMapper;
    private final RecipeFoodEntityMapper recipeFoodEntityMapper;
    private final RecipeProcessEntityMapper recipeProcessEntityMapper;
    private final FoodRepository foodRepository;
    private final S3Provider s3Provider;
    private final RecipeLikeRepository recipeLikeRepository;
    private final FollowRepository followRepository;

    private final int PAGE_SIZE = 9;

    @Override
    @Transactional
    public void createRecipe(
        final RecipeCreateServiceRequestDto recipeCreateServiceRequestDto,
        final MultipartFile recipeImage,
        final List<RecipeFoodCreateServiceRequestDto> recipeFoodCreateServiceRequestDtos,
        final List<RecipeProcessCreateServiceRequestDto> recipeProcessCreateServiceRequestDtos,
        final List<MultipartFile> recipeProcessImage,
        final User user
    ) throws IOException {
        String recipeFolderName = recipeCreateServiceRequestDto.title() + UUID.randomUUID();
        String recipeImageName =
            recipeFolderName + S3Provider.SEPARATOR + s3Provider.originalFileName(recipeImage);
        Recipe recipe = recipeEntityMapper.toRecipe(recipeCreateServiceRequestDto, user,
            recipeFolderName, s3Provider.getImagePath(recipeImageName));

        List<RecipeFood> recipeFoods = recipeFoodCreateServiceRequestDtos.stream()
            .map(recipeFoodDto -> {
                    Food food = foodRepository.findByName(recipeFoodDto.foodName())
                        .orElseThrow(() -> new NotFoundFoodException(
                            FoodErrorCode.NOT_FOUND_FOOD));
                    return recipeFoodEntityMapper.toRecipeFood(recipeFoodDto, food, recipe);
                }
            ).toList();
        recipe.getRecipeFoods().addAll(recipeFoods);

        List<RecipeProcess> recipeProcesses = new ArrayList<>();
        List<String> recipeProcessImageNames = new ArrayList<>();
        for (int i = 0; i < recipeProcessCreateServiceRequestDtos.size(); i++) {
            String recipeProcessImageName =
                s3Provider.originalFileName(recipeProcessImage.get(i));
            String recipeProcessImageUrl = null;
            if (!recipeProcessImageName.isEmpty()) {
                recipeProcessImageUrl = s3Provider
                    .getImagePath(recipeFolderName + S3Provider.SEPARATOR + recipeProcessImageName);
            }
            recipeProcessImageNames.add(recipeProcessImageName);
            recipeProcesses.add(recipeProcessEntityMapper.toRecipeProcess(
                recipeProcessCreateServiceRequestDtos.get(i), recipeProcessImageUrl, recipe));
        }
        recipe.getRecipeProcesses().addAll(recipeProcesses);

        recipeRepository.save(recipe);
        saveImage(recipe.getFolderName(), recipeImage, recipeImageName, recipeProcessImage,
            recipeProcessImageNames);
    }

    private void saveImage(
        String recipeFolderName,
        MultipartFile recipeImage,
        String recipeImageName,
        List<MultipartFile> recipeProcessImage,
        List<String> recipeProcessImageName
    ) throws IOException {
        s3Provider.createFolder(recipeFolderName);
        s3Provider.saveFile(recipeImage, recipeImageName);
        for (int i = 0; i < recipeProcessImage.size(); i++) {
            if (!recipeProcessImage.get(i).isEmpty()) {
                s3Provider.saveFile(recipeProcessImage.get(i),
                    recipeFolderName + S3Provider.SEPARATOR + recipeProcessImageName.get(i));
            }
        }
    }

    @Transactional
    @Override
    public void deleteRecipe(Long recipeId, User user) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        recipeRepository.delete(recipe);
        s3Provider.delete(recipe.getFolderName() + S3Provider.SEPARATOR);
        // TODO 폴더 삭제시키기
    }

    @Override
    @Transactional
    public void updateRecipe(final RecipeUpdateServiceRequestDto requestDto, User user,
        Long recipeId, MultipartFile multipartFile) throws IOException {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        if (!requestDto.imageChange()) {
            recipe.updateRecipe(
                requestDto.title(),
                requestDto.intro(),
                requestDto.serving(),
                requestDto.videoUrl(),
                recipe.getImageUrl() //TODO 이미지 URL 넣기
            );
        } else {
            String imageName = s3Provider.updateImage(recipe.getImageUrl(),
                recipe.getFolderName(), multipartFile);
            recipe.updateRecipe(
                requestDto.title(),
                requestDto.intro(),
                requestDto.serving(),
                requestDto.videoUrl(),
                imageName //TODO 이미지 URL 넣기
            );
        }
    }

    @Transactional(readOnly = true)
    public List<RecipeAllReadResponseDto> readAllRecipe(final Integer page,
        final UserDetailsImpl userDetails) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(Direction.DESC, "id"));
        return recipeRepository.findAllSliceBy(pageRequest)
            .stream()
            .map(recipe -> readDto(recipe, userDetails))
            .toList();
    }

    private RecipeAllReadResponseDto readDto(Recipe recipe, UserDetailsImpl userDetails) {
        RecipeAllReadResponseDtoBuilder builder = RecipeAllReadResponseDto.builder()
            .id(recipe.getId())
            .title(recipe.getTitle())
            .intro(recipe.getIntro())
            .serving(recipe.getServing())
            .imageUrl(recipe.getImageUrl())
            .writer(recipe.getUser().getNickname())
            .likeCount(recipe.getRecipeLikes().size());
        if (userDetails == null) {
            builder.isLiked(false);
        } else {
            boolean check = recipe.getRecipeLikes()
                .stream()
                .anyMatch(
                    recipeLike -> recipeLike.getUser().getId().equals(userDetails.user().getId()));
            builder.isLiked(check);
        }
        return builder.build();
    }

    @Transactional(readOnly = true)
    public RecipeReadResponseDto readRecipe(Long recipeId, UserDetailsImpl userDetails) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeReadResponseDtoBuilder builder = RecipeReadResponseDto.builder()
            .id(recipe.getId())
            .title(recipe.getTitle())
            .serving(recipe.getServing())
            .imageUrl(recipe.getImageUrl())
            .writer(recipe.getUser().getNickname())
            .imageUrl(recipe.getImageUrl())
            .writerId(recipe.getUser().getId())
            .intro(recipe.getIntro())
            .videoUrl(recipe.getVideoUrl());
        if (userDetails == null) {
            builder.isFollowed(false);
        } else {
            builder.isFollowed(
                followRepository.existsByUserIdAndFollowingId(userDetails.user().getId(),
                    recipe.getUser().getId()));
        }
        return builder.build();
    }
}
