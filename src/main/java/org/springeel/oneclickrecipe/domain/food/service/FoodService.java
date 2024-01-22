package org.springeel.oneclickrecipe.domain.food.service;

import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

public interface FoodService {

    void createFood(FoodCreateServiceRequestDto requestDto, User user);

    void deleteFood(User user, Long id);
}
