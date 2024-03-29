package org.springeel.oneclickrecipe.domain.recipefood.mapper.service;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;

@Mapper(componentModel = SPRING)
public interface RecipeFoodEntityMapper {

    RecipeFood toRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Food food,
        Recipe recipe);
}
