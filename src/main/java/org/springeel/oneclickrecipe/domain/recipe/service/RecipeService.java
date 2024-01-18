package org.springeel.oneclickrecipe.domain.recipe.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeService {

    void createRecipe(RecipeCreateServiceRequestDto controllerRequestDto, User user);

    void deleteRecipe(Long recipeId, User user);

    void updateRecipe(RecipeUpdateServiceRequestDto requestDto, User user, Long recipeId);

    List<RecipeAllReadResponseDto> readAllRecipe();
}
