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
        // recipeFood가 있는지 검증
        RecipeFood recipeFood = recipeFoodRepository.findById(recipeFoodId).orElseThrow(
            () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));

        // 요청자가 레시피를 만든 사람인지 검증
        if (!recipeFood.getRecipe().getUser().getId().equals(user.getId())) {
            throw new ForbiddenAccessRecipeFoodException(
                RecipeFoodErrorCode.FORBIDDEN_ACCESS_RECIPEFOOD);
        }

        recipeFoodRepository.delete(recipeFood);

//        Recipe recipe = recipeRepository.findByIdAndUser(recipeId, user)
//            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
//        RecipeFood recipeFood = recipeFoodRepository.findByIdAndRecipe(recipeFoodId, recipe)
//            .orElseThrow(
//                () -> new NotFoundRecipeFoodException(RecipeFoodErrorCode.NOT_FOUND_RECIPEFOOD));
//        recipeFoodRepository.delete(recipeFood);
    }

    // TODO recipeId 제거해보는 리팩토링 해보기
    @Override
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

    @Override
    public List<RecipeFoodReadResponseDto> readRecipeFood(
        Long recipeId
    ) {
        return recipeFoodRepository.findAllByRecipeId(recipeId)
            .stream()
            .map(recipeFood -> RecipeFoodReadResponseDto.builder()
                .name(recipeFood.getFood().getName())
                .amount(recipeFood.getAmount())
                .price(recipeFood.getAmount() * recipeFood.getFood().getPrice())
                .unit(recipeFood.getFood().getUnit())
                .build())
            .toList();
    }
}
