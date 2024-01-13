package org.springeel.oneclickrecipe.domain.recipefood.mapper.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;

@Mapper(componentModel = ComponentModel.SPRING)

public interface RecipeFoodEntityMapper {

    RecipeFood toRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Food food,
        Recipe recipe);

}
