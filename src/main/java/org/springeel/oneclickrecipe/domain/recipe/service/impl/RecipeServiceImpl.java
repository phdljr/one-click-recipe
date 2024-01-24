package org.springeel.oneclickrecipe.domain.recipe.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.mapper.entity.RecipeEntityMapper;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.util.S3Provider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEntityMapper recipeEntityMapper;
    private final S3Provider s3Provider;

    // TODO S3 사용하는 부분 메소드로 분리해두기
    @Override
    public RecipeCreateResponseDto createRecipe(final RecipeCreateServiceRequestDto requestDto, User user,
        MultipartFile multipartFile) throws IOException {
        String fileName;
        String fileUrl;
        String folderName = requestDto.title() + UUID.randomUUID();
        if (multipartFile.isEmpty()) {
            fileUrl = null;
            fileName = null;
        } else {
            fileName = s3Provider.originalFileName(multipartFile);
            fileUrl = s3Provider.url + folderName + s3Provider.SEPARATOR + fileName;
        }
        Recipe recipe = recipeEntityMapper.toRecipe(requestDto, user, folderName, fileUrl);
        recipe = recipeRepository.save(recipe);
        if (fileUrl == null) {
            s3Provider.createFolder(folderName);
        } else {
            s3Provider.createFolder(folderName);
            fileUrl = folderName + s3Provider.SEPARATOR + fileName;
            s3Provider.saveFile(multipartFile, fileUrl);
        }
        return recipeEntityMapper.toRecipeCreateResponseDto(recipe);
    }

    @Transactional
    @Override
    public void deleteRecipe(Long recipeId, User user) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        recipeRepository.delete(recipe);
        s3Provider.delete(recipe.getFolderName() + "/");
        // TODO 폴더 삭제시키기
    }

    @Override
    @Transactional
    public void updateRecipe(final RecipeUpdateServiceRequestDto requestDto, User user,
        Long recipeId, MultipartFile multipartFile) throws IOException {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
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

    public List<RecipeAllReadResponseDto> readAllRecipe() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipeEntityMapper.toRecipeAllReadResponseDto(recipes);
    }

    public RecipeReadResponseDto readRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        return recipeEntityMapper.toRecipeReadResponseDto(recipe);
    }
}
