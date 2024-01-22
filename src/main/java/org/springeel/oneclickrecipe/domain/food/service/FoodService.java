package org.springeel.oneclickrecipe.domain.food.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.response.FoodReadAllServiceResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface FoodService {

    void createFood(FoodCreateServiceRequestDto requestDto, User user);

    void deleteFood(User user, Long id);

    void updateFood(User user, Long id, FoodUpdateServiceRequestDto requestDto);

    List<FoodReadAllServiceResponseDto> readAllFoods(User user);
}
