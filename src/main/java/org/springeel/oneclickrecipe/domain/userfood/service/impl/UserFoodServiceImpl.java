package org.springeel.oneclickrecipe.domain.userfood.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.exception.NotFoundUserException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.response.UserFoodReadAllServiceResponseDto;
import org.springeel.oneclickrecipe.domain.userfood.entity.UserFood;
import org.springeel.oneclickrecipe.domain.userfood.exception.UserFoodErrorCode;
import org.springeel.oneclickrecipe.domain.userfood.exception.NotFoundUserFoodException;
import org.springeel.oneclickrecipe.domain.userfood.mapper.service.UserFoodEntityMapper;
import org.springeel.oneclickrecipe.domain.userfood.repository.UserFoodRepository;
import org.springeel.oneclickrecipe.domain.userfood.service.UserFoodService;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFoodServiceImpl implements UserFoodService {

    private final UserFoodRepository userFoodRepository;
    private final UserRepository userRepository;
    private final UserFoodEntityMapper userFoodEntityMapper;

    public void createFood(UserFoodCreateServiceRequestDto requestDto, User user) {
        User member = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        UserFood userFood = userFoodEntityMapper.toUserFood(requestDto, member);
        userFoodRepository.save(userFood);
    }

    public void deleteFood(Long id, User user) {
        User member = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        if (member.getRole().equals(UserRole.USER)) {
            throw new NotFoundUserException(UserErrorCode.BAD_ROLE);
        }
        UserFood userFood = userFoodRepository.findById(id)
            .orElseThrow(() -> new NotFoundUserFoodException(UserFoodErrorCode.NOT_FOUND_FOOD));
        userFoodRepository.delete(userFood);
    }

    @Transactional
    public void updateFood(Long id, UserFoodUpdateServiceRequestDto requestDto, User user) {
        User member = userRepository.findById(user.getId())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        if (member.getRole().equals(UserRole.USER)) {
            throw new NotFoundUserException(UserErrorCode.BAD_ROLE);
        }
        UserFood userFood = userFoodRepository.findByName(requestDto.name())
            .orElseThrow(() -> new NotFoundUserFoodException(UserFoodErrorCode.NOT_FOUND_FOOD));
        userFood.updateFood(requestDto.name(), requestDto.unit());
    }

    public List<UserFoodReadAllServiceResponseDto> readAllFoods() {
        List<UserFood> userFoods = userFoodRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return userFoodEntityMapper.toFooReadAllResponseDto(userFoods);
    }

    private UserFood findFood(Long id) {
        return userFoodRepository.findById(id)
            .orElseThrow(() -> new NotFoundUserFoodException(UserFoodErrorCode.NOT_FOUND_FOOD));
    }
}
