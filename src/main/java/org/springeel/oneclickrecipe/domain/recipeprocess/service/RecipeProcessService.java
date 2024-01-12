package org.springeel.oneclickrecipe.domain.recipeprocess.service;

import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeProcessService {

    void createRecipeProcess(RecipeProcessCreateServiceRequestDto requestDto, User user,
        Long recipe_Id);

    void deleteRecipeProcess(Long recipeId, User user, Long processId);

    void updateRecipeProcess(RecipeProcessUpdateServiceRequestDto requestDto, Long recipeId,
        User user, Long processId);

}
