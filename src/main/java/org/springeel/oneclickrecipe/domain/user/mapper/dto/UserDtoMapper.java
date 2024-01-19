package org.springeel.oneclickrecipe.domain.user.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserLoginControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserSignUpControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserSignUpServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface UserDtoMapper {

    UserSignUpServiceRequestDto toUserSignUpServiceRequestDto(
        UserSignUpControllerRequestDto controllerRequestDto);

    UserLoginServiceRequestDto toUserLoginServiceRequestDto(
        UserLoginControllerRequestDto controllerRequestDto);
}
