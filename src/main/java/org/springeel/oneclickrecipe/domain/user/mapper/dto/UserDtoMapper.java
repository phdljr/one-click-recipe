package org.springeel.oneclickrecipe.domain.user.mapper.dto;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.user.dto.controller.*;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserDtoMapper {

    NicknameUpdateServiceRequestDto toNicknameUpdateServiceRequestDto(
        NicknameUpdateControllerRequestDto controllerRequestDto);

    UserSignUpServiceRequestDto toUserSignUpServiceRequestDto(
        UserSignUpControllerRequestDto controllerRequestDto);

    UserLoginServiceRequestDto toUserLoginServiceRequestDto(
        UserLoginControllerRequestDto controllerRequestDto);

    PasswordUpdateServiceRequestDto toPasswordUpdateServiceRequestDto(
        PasswordUpdateControllerRequestDto controllerRequestDto);

    DeleteUserServiceRequestDto toDeleteUserServiceRequestDto(
        DeleteUserControllerRequestDto controllerRequestDto);
}
