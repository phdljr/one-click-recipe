package org.springeel.oneclickrecipe.domain.recipeprocess.service.impl;


import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.NotFoundRecipeProcessException;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.RecipeProcessErrorCode;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.ValidateRecipeProcessException;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity.RecipeProcessEntityMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.repository.RecipeProcessRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.util.S3Provider;
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


    public void createRecipeProcess(
        final RecipeProcessCreateServiceRequestDto requestDto,
        User user,
        Long recipeId,
        MultipartFile multipartFile
    ) throws IOException {
        Boolean validator = recipeProcessRepository.existsBySequence(requestDto.sequence());
        if (validator) {
            throw new ValidateRecipeProcessException(RecipeProcessErrorCode.USE_VALIDATE_DATA);
        }
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        String folderName = recipe.getFolderName();
        String fileName;
        String fileUrl;
        if (multipartFile.isEmpty()) {
            fileName = null;
            fileUrl = null;
        } else {
            fileName = s3Provider.originalFileName(multipartFile);
            fileUrl = url + folderName + SEPARATOR + fileName;
        }
        RecipeProcess recipeProcess = recipeProcessEntityMapper.toRecipeProcess(requestDto,
            fileUrl, recipe);
        recipeProcessRepository.save(recipeProcess);
        fileUrl = recipe.getFolderName() + SEPARATOR + fileName;
        s3Provider.saveFile(multipartFile, fileUrl);
    }

    public void deleteRecipeProcess(
        Long recipeId,
        User user,
        Long processId
    ) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeProcess recipeProcess = recipeProcessRepository.findByIdAndRecipe(processId, recipe)
            .orElseThrow(() -> new NotFoundRecipeProcessException(
                RecipeProcessErrorCode.NOT_FOUND_RECIPE_PROCESS));
        String imageName = recipeProcess.getImageUrl().replace(url, "");
        imageName = imageName.substring(imageName.lastIndexOf("/"));
        recipeProcessRepository.delete(recipeProcess);
        s3Provider.deleteImage(recipe.getFolderName() + imageName);
    }

    @Transactional
    public void updateRecipeProcess(
        final RecipeProcessUpdateServiceRequestDto requestDto,
        Long recipeId,
        User user,
        Long processId,
        MultipartFile multipartFile
    ) throws IOException {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeProcess recipeProcess = recipeProcessRepository.findByIdAndRecipe(processId, recipe)
            .orElseThrow(() -> new NotFoundRecipeProcessException(
                RecipeProcessErrorCode.NOT_FOUND_RECIPE_PROCESS));
        String imageName = s3Provider.updateImage(recipeProcess.getImageUrl(),
            recipe.getFolderName(), multipartFile);
        recipeProcess.updateRecipe(
            requestDto.sequence(),
            requestDto.description(),
            requestDto.time(),
            imageName
        );
    }

    public List<RecipeProcessReadResponseDto> readRecipeProcess(
        Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        List<RecipeProcess> recipeProcesses = recipeProcessRepository.findAllByRecipeOrderBySequenceAsc(
            recipe);
        List<RecipeProcessReadResponseDto> readRecipeProcess =
            recipeProcessEntityMapper.toReadRecipeProcess(recipeProcesses);
        return readRecipeProcess;
    }
}
