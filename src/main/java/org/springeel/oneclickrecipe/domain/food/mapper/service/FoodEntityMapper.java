package org.springeel.oneclickrecipe.domain.food.mapper.service;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;

@Mapper(componentModel = SPRING)

public interface FoodEntityMapper {

    Food toFood(FoodCreateServiceRequestDto requestDto);

}
