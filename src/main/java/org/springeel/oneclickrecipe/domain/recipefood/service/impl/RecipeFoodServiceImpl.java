package org.springeel.oneclickrecipe.domain.recipefood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.exception.FoodErrorCode;
import org.springeel.oneclickrecipe.domain.food.exception.NotFoundFoodException;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.service.RecipeFoodEntityMapper;
import org.springeel.oneclickrecipe.domain.recipefood.repository.RecipeFoodRepository;
import org.springeel.oneclickrecipe.domain.recipefood.service.RecipeFoodService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeFoodServiceImpl implements RecipeFoodService {

    private final RecipeFoodRepository recipeFoodRepository;
    private final FoodRepository foodRepository;
    private final RecipeFoodEntityMapper recipeFoodEntityMapper;
    private final RecipeRepository recipeRepository;

    public void createRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Long recipeId) {
        Food food = foodRepository.findByName(requestDto.foodName())
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeFood recipeFood = recipeFoodEntityMapper.toRecipeFood(requestDto, food,
            recipe);
        recipeFoodRepository.save(recipeFood);
    }
}
