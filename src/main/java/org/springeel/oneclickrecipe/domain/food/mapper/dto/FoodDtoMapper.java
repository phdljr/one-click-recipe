package org.springeel.oneclickrecipe.domain.food.mapper.dto;

import org.springeel.oneclickrecipe.domain.food.dto.controller.FoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;

public interface FoodDtoMapper {

    FoodCreateServiceRequestDto toFoodCreateServiceDto(
        FoodCreateControllerRequestDto controllerDto);


}
