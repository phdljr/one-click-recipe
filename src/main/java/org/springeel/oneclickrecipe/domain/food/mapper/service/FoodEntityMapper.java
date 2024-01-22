package org.springeel.oneclickrecipe.domain.food.mapper.service;

import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;

public interface FoodEntityMapper {

    Food toFood(FoodCreateServiceRequestDto requestDto);

}
