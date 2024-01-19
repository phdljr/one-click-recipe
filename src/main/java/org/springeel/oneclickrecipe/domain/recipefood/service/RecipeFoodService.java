package org.springeel.oneclickrecipe.domain.recipefood.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.response.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeFoodService {

    void createRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Long recipeId, User user);

    void deleteRecipeFood(Long recipeFoodId, User user);

    void updateRecipeFood(Long recipeId, Long recipeFoodId, User user,
        RecipeFoodUpdateServiceRequestDto requestDto);

    List<RecipeFoodReadResponseDto> readRecipeFood(Long recipeId);
}
