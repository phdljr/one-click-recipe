package org.springeel.oneclickrecipe.domain.recipeprocess.service.impl;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.NotFoundRecipeProcessException;
import org.springeel.oneclickrecipe.domain.recipeprocess.exception.RecipeProcessErrorCode;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity.RecipeProcessEntityMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.repository.RecipeProcessRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.util.S3Provider;
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

    public void createRecipeProcess(
        final RecipeProcessCreateServiceRequestDto requestDto,
        User user,
        Long recipeId,
        MultipartFile multipartFile
    ) throws IOException {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        String folderName = recipe.getTitle();
        folderName = URLEncoder.encode(folderName, StandardCharsets.UTF_8);
        String fileName = s3Provider.originalFileName(multipartFile);
        String fileUrl = url + folderName + SEPARATOR + fileName;
        RecipeProcess recipeProcess = recipeProcessEntityMapper.toRecipeProcess(requestDto,
            fileUrl, recipe);
        recipeProcessRepository.save(recipeProcess);
        fileUrl = recipe.getTitle() + SEPARATOR + fileName;
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
        recipeProcessRepository.delete(recipeProcess);
    }

    @Transactional
    public void updateRecipeProcess(
        final RecipeProcessUpdateServiceRequestDto requestDto,
        Long recipeId,
        User user,
        Long processId
    ) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeProcess recipeProcess = recipeProcessRepository.findByIdAndRecipe(processId, recipe)
            .orElseThrow(() -> new NotFoundRecipeProcessException(
                RecipeProcessErrorCode.NOT_FOUND_RECIPE_PROCESS));

        recipeProcess.updateRecipe(
            requestDto.sequence(),
            requestDto.description(),
            requestDto.time(),
            requestDto.imageUrl()
        );

    }
}
