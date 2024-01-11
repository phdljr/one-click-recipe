package org.springeel.oneclickrecipe.domain.recipeprocess.service.impl;


import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity.RecipeProcessEntityMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.repository.RecipeProcessRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeProcessServiceImpl implements RecipeProcessService {

    private final RecipeProcessRepository recipeProcessRepository;
    private final RecipeProcessEntityMapper recipeProcessEntityMapper;
    private final RecipeRepository recipeRepository;

    public void createRecipeProcess(
        final RecipeProcessCreateServiceRequestDto requestDto,
        User user,
        Long recipeId
    ) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeProcess recipeProcess = recipeProcessEntityMapper.toRecipeProcess(requestDto, recipe);
        recipeProcessRepository.save(recipeProcess);
    }
}
