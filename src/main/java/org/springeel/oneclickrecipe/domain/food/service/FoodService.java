package org.springeel.oneclickrecipe.domain.food.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.response.FoodReadAllServiceResponseDto;

public interface FoodService {

    void createFood(FoodCreateServiceRequestDto requestDto);

    void deleteFood(Long id);

    void updateFood(Long id, FoodUpdateServiceRequestDto requestDto);

    List<FoodReadAllServiceResponseDto> readAllFoods();
}
