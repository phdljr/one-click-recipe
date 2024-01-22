package org.springeel.oneclickrecipe.domain.food.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.response.FoodReadAllServiceResponseDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.exception.FoodErrorCode;
import org.springeel.oneclickrecipe.domain.food.exception.NotFoundFoodException;
import org.springeel.oneclickrecipe.domain.food.mapper.service.FoodEntityMapper;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.food.service.FoodService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.exception.NotFoundUserException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final FoodEntityMapper foodEntityMapper;

    public void createFood(FoodCreateServiceRequestDto requestDto, User user) {
        User admin = findUserRole(user);
        Food food = foodEntityMapper.toFood(requestDto);
        foodRepository.save(food);
    }

    public void deleteFood(User user, Long id) {
        User admin = findUserRole(user);
        Food food = foodRepository.findById(id)
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        foodRepository.delete(food);
    }

    @Transactional
    public void updateFood(User user, Long id, FoodUpdateServiceRequestDto requestDto) {
        User admin = findUserRole(user);
        Food food = findFood(id);
        food.updateFood(requestDto.name(), requestDto.price(), requestDto.unit());
    }

    public List<FoodReadAllServiceResponseDto> readAllFoods(User user) {
        User admin = findUserRole(user);
        List<Food> foods = foodRepository.findAll();
        return foodEntityMapper.toFoodAll(foods);
    }

    private User findUserRole(User user) {
        User admin = userRepository.findByRole(user.getRole())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        return admin;
    }

    private Food findFood(Long id) {
        Food food = foodRepository.findById(id)
            .orElseThrow(() -> new NotFoundFoodException(FoodErrorCode.NOT_FOUND_FOOD));
        return food;
    }
}
