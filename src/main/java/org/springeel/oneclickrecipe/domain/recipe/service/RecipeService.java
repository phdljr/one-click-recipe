package org.springeel.oneclickrecipe.domain.recipe.service;

import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeDeleteServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeService {

    void createRecipe(RecipeCreateServiceRequestDto controllerRequestDto, User user);

    void deleteRecipe(RecipeDeleteServiceRequestDto requestDto, User user);

}
