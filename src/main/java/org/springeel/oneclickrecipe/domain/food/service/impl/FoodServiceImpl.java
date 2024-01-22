package org.springeel.oneclickrecipe.domain.food.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.mapper.service.FoodEntityMapper;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.food.service.FoodService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.exception.NotFoundUserException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final FoodEntityMapper foodEntityMapper;
    
    public void CreateFood(FoodCreateServiceRequestDto requestDto, User user) {
        userRepository.findByRole(user.getRole())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        Food food = foodEntityMapper.toFood(requestDto);
        foodRepository.save(food);
    }
}
