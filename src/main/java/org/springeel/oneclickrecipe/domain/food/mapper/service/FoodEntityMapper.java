package org.springeel.oneclickrecipe.domain.food.mapper.service;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.response.FoodReadAllServiceResponseDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;

@Mapper(componentModel = SPRING)

public interface FoodEntityMapper {

    Food toFood(FoodCreateServiceRequestDto requestDto);

    List<FoodReadAllServiceResponseDto> toFoodAll(List<Food> foods);

}
