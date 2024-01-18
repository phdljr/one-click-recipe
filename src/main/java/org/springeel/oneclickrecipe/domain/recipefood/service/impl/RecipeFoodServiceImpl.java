package org.springeel.oneclickrecipe.domain.recipefood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.exception.FoodErrorCode;
import org.springeel.oneclickrecipe.domain.food.exception.NotFoundFoodException;
import org.springeel.oneclickrecipe.domain.food.mapper.FoodEntityMapper;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.exception.NotFoundRecipeFoodException;
import org.springeel.oneclickrecipe.domain.recipefood.exception.RecipeFoodErrorCode;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.service.RecipeFoodEntityMapper;
import org.springeel.oneclickrecipe.domain.recipefood.repository.RecipeFoodRepository;
import org.springeel.oneclickrecipe.domain.recipefood.service.RecipeFoodService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RecipeFoodServiceImpl implements RecipeFoodService {

    private final RecipeFoodRepository recipeFoodRepository;
    private final FoodRepository foodRepository;
    private final RecipeFoodEntityMapper recipeFoodEntityMapper;
    private final RecipeRepository recipeRepository;
    private final FoodEntityMapper foodEntityMapper;

    public void createRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Long recipeId,
        User user) {
        Food food = foodRepository.findByName(requestDto.foodName())
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeFood recipeFood = recipeFoodEntityMapper.toRecipeFood(requestDto, food,
            recipe);
        recipeFoodRepository.save(recipeFood);
    }

    public void deleteRecipeFood(Long recipeId, Long recipeFoodId, User user) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe(recipeFoodId, recipe)
            .orElseThrow(
                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
        recipeFoodRepository.delete(recipeFood);
    }

    @Transactional
    public void updateRecipeFood(Long recipeId, Long recipeFoodId, User user,
        RecipeFoodUpdateServiceRequestDto requestDto) {
        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
            .orElseThrow(() -> new NotFoundRecipeFoodException(RecipeErrorCode.NOT_FOUND_RECIPE));
        Food food = foodRepository.findByName(requestDto.foodName())
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe(recipeFoodId, recipe)
            .orElseThrow(
                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
        recipeFood.updateRecipeFood(
            requestDto.amount(),
            recipe,
            food
        );
    }

    public RecipeFoodReadResponseDto readRecipeFood(
        Long recipeId,
        Long recipeFoodId
    ) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe(recipeFoodId, recipe)
            .orElseThrow(
                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
        Food food = foodRepository.findById(recipeFood.getId())
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        Float total = recipeFood.getAmount() * food.getPrice();
        return recipeFoodEntityMapper.toReadRecipeFood(food.getName(), recipeFood.getAmount(),
            total, food.getUnit());
    }
}
