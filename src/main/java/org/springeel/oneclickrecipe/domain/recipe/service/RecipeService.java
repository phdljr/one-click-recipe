package org.springeel.oneclickrecipe.domain.recipe.service;

import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;

public interface RecipeService {

    void createRecipe(RecipeCreateServiceRequestDto controllerRequestDto, Long userId);

}
