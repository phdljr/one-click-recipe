package org.springeel.oneclickrecipe.domain.recipefood.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.exception.FoodErrorCode;
import org.springeel.oneclickrecipe.domain.food.exception.NotFoundFoodException;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.response.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.exception.ForbiddenAccessRecipeFoodException;
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

    @Override
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

    @Override
    public void deleteRecipeFood(Long recipeFoodId, User user) {
        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe_User(recipeFoodId, user)
            .orElseThrow(
                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
        recipeFoodRepository.delete(recipeFood);
    }

    // TODO recipeId 제거해보는 리팩토링 해보기
    @Override
    @Transactional
    public void updateRecipeFood(Long recipeFoodId, User user,
        RecipeFoodUpdateServiceRequestDto requestDto) {
        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe_User(recipeFoodId, user)
            .orElseThrow(
                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
        Food food = foodRepository.findByName(requestDto.foodName())
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        recipeFood.updateRecipeFood(
            requestDto.amount(),
            food
        );
    }

    @Override
    public List<RecipeFoodReadResponseDto> readRecipeFood(
        Long recipeId
    ) {
        return recipeFoodRepository.findAllByRecipeId(recipeId)
            .stream()
            .map(recipeFood -> RecipeFoodReadResponseDto.builder()
                .id(recipeFood.getId())
                .name(recipeFood.getFood().getName())
                .amount(recipeFood.getAmount())
                .price(recipeFood.getAmount() * recipeFood.getFood().getPrice())
                .unit(recipeFood.getFood().getUnit())
                .build())
            .toList();
    }
}
