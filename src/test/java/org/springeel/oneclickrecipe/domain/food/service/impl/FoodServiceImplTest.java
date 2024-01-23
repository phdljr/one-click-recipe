/*
package org.springeel.oneclickrecipe.domain.food.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springeel.oneclickrecipe.domain.food.dto.service.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;
import org.springeel.oneclickrecipe.domain.food.mapper.service.FoodEntityMapper;
import org.springeel.oneclickrecipe.domain.food.repository.FoodRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodServiceImplTest {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodEntityMapper foodEntityMapper;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
            .nickname("test")
            .email("test@test.test")
            .password("12345678")
            .role(UserRole.ADMIN).build();
    }

    @Test
    @DisplayName("식재료 추가 테스트")
    void createFood() {
        //given
        FoodCreateServiceRequestDto requestDto =
            new FoodCreateServiceRequestDto("고구마", 100, UnitType.G);
        Food food = foodEntityMapper.toFood(requestDto);
        Food result;
        UserRole role = user.getRole();
        // when
        if (role.equals(UserRole.ADMIN)) {
            result = foodRepository.save(food);
        } else {
            result = null;
        }
        // then
        assertNotNull(result, "Null");
    }
}*/
