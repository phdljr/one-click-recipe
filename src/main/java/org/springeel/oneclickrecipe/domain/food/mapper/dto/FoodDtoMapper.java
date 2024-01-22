package org.springeel.oneclickrecipe.domain.food.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.food.dto.controller.FoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.controller.FoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)

public interface FoodDtoMapper {

    FoodCreateServiceRequestDto toFoodCreateServiceDto(
        FoodCreateControllerRequestDto controllerDto);

    FoodUpdateServiceRequestDto toFoodUpdateServiceDto(
        FoodUpdateControllerRequestDto controllerDto);

}
