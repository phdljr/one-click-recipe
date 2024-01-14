package org.springeel.oneclickrecipe.domain.recipefood.service;

import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeFoodService {

    void createRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Long recipeId, User user);

    void deleteRecipeFood(Long recipeFoodId, Long recipeId, User user);

}
