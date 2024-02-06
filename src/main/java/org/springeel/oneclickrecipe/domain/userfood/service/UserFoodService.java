package org.springeel.oneclickrecipe.domain.userfood.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.response.UserFoodReadAllServiceResponseDto;

public interface UserFoodService {

    void createFood(UserFoodCreateServiceRequestDto requestDto, User user);

    void deleteFood(Long id, User user);

    void updateFood(Long id, UserFoodUpdateServiceRequestDto requestDto, User user);

    List<UserFoodReadAllServiceResponseDto> readAllFoods();
}
