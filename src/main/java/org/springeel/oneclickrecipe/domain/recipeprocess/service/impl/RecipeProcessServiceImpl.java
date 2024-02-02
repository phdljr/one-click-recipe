package org.springeel.oneclickrecipe.domain.recipeprocess.service.impl;


import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.response.RecipeProcessReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.NotFoundRecipeProcessException;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.RecipeProcessErrorCode;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.ValidateRecipeProcessException;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity.RecipeProcessEntityMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.repository.RecipeProcessRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.s3.S3Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class RecipeProcessServiceImpl implements RecipeProcessService {

    private final RecipeProcessRepository recipeProcessRepository;
    private final RecipeProcessEntityMapper recipeProcessEntityMapper;
    private final RecipeRepository recipeRepository;
    private final S3Provider s3Provider;
    private final String SEPARATOR = "/";
    private final String url = "https://onceclick.s3.ap-northeast-2.amazonaws.com/";
    @Value("${cloud.aws.s3.bucket.name}")
    public String bucket;

    @Override
    public void createRecipeProcess(
        final RecipeProcessCreateServiceRequestDto requestDto,
        User user,
        Long recipeId,
        MultipartFile multipartFile
    ) throws IOException {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        String folderName = recipe.getFolderName();
        String fileName;
        String fileUrl;
        RecipeProcess check = recipeProcessRepository.findByRecipeAndSequence(recipe,
            requestDto.sequence());
        if (check != null) {
            throw new ValidateRecipeProcessException(RecipeProcessErrorCode.USE_VALIDATE_DATA);
        }
        if (multipartFile.isEmpty()) {
            fileUrl = null;
            RecipeProcess recipeProcess = recipeProcessEntityMapper.toRecipeProcess(
                requestDto, fileUrl, recipe);
            recipeProcessRepository.save(recipeProcess);
        } else {
            fileName = s3Provider.originalFileName(multipartFile);
            fileUrl = url + folderName + SEPARATOR + fileName;
            RecipeProcess recipeProcess = recipeProcessEntityMapper.toRecipeProcess(requestDto,
                fileUrl, recipe);
            recipeProcessRepository.save(recipeProcess);
            fileUrl = recipe.getFolderName() + SEPARATOR + fileName;
            s3Provider.saveFile(multipartFile, fileUrl);
        }

    }

    @Override
    public void deleteRecipeProcess(
        User user,
        Long processId
    ) {
        RecipeProcess recipeProcess = recipeProcessRepository.findByIdAndRecipe_User(processId,
                user)
            .orElseThrow(() -> new NotFoundRecipeProcessException(
                RecipeProcessErrorCode.NOT_FOUND_RECIPE_PROCESS));
        if (recipeProcess.getImageUrl() == null) {
            recipeProcessRepository.delete(recipeProcess);
        } else {
            String imageName = recipeProcess.getImageUrl().replace(url, "");
            imageName = imageName.substring(imageName.lastIndexOf("/"));
            recipeProcessRepository.delete(recipeProcess);
            s3Provider.delete(recipeProcess.getRecipe().getFolderName() + imageName);
        }
    }

    @Override
    @Transactional
    public void updateRecipeProcess(
        final RecipeProcessUpdateServiceRequestDto requestDto,
        User user,
        Long processId,
        MultipartFile multipartFile
    ) throws IOException {
        RecipeProcess recipeProcess = recipeProcessRepository.findByIdAndRecipe_User(processId,
                user)
            .orElseThrow(() -> new NotFoundRecipeProcessException(
                RecipeProcessErrorCode.NOT_FOUND_RECIPE_PROCESS));
        if (!requestDto.imageChange()) {
            recipeProcess.updateRecipe(
                requestDto.sequence(),
                requestDto.description(),
                recipeProcess.getImageUrl()
            );
        } else {
            String imageName = s3Provider.updateImage(recipeProcess.getImageUrl(),
                recipeProcess.getRecipe().getFolderName(), multipartFile);
            recipeProcess.updateRecipe(
                requestDto.sequence(),
                requestDto.description(),
                imageName
            );
        }
    }

    @Override
    public List<RecipeProcessReadResponseDto> readRecipeProcess(
        Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        List<RecipeProcess> recipeProcesses = recipeProcessRepository.findAllByRecipeOrderBySequenceAsc(
            recipe);
        return recipeProcessEntityMapper.toRecipeProcessReadResponseDto(recipeProcesses);
    }
}
