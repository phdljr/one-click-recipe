package org.springeel.oneclickrecipe.domain.recipefood.service;

import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;

public interface RecipeFoodService {

    void createRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Long recipeId);

}
