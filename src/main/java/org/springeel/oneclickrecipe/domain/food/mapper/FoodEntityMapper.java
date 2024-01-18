package org.springeel.oneclickrecipe.domain.food.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.food.dto.FoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;

@Mapper(componentModel = ComponentModel.SPRING)
public interface FoodEntityMapper {

    List<FoodReadResponseDto> toReadRecipe(List<Food> food);
}
