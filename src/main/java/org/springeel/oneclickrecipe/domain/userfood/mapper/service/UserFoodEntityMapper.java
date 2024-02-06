package org.springeel.oneclickrecipe.domain.userfood.mapper.service;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.response.UserFoodReadAllServiceResponseDto;
import org.springeel.oneclickrecipe.domain.userfood.entity.UserFood;

@Mapper(componentModel = SPRING)
public interface UserFoodEntityMapper {

    UserFood toUserFood(UserFoodCreateServiceRequestDto requestDto, User user);

    List<UserFoodReadAllServiceResponseDto> toFooReadAllResponseDto(List<UserFood> userFoods);

}
