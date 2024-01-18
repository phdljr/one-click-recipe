package org.springeel.oneclickrecipe.domain.recipefood.mapper.service;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;

@Mapper(componentModel = ComponentModel.SPRING)

public interface RecipeFoodEntityMapper {

    RecipeFood toRecipeFood(RecipeFoodCreateServiceRequestDto requestDto, Food food,
        Recipe recipe);

    RecipeFoodReadResponseDto toReadRecipeFood(String name, Short amount, Float price,
        UnitType unit
    );

}
