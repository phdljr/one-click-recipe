package org.springeel.oneclickrecipe.domain.user.mapper.dto;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.user.dto.controller.NicknameUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.controller.PasswordUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserLoginControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserSignUpControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.PasswordUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.NicknameUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserSignUpServiceRequestDto;

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
}
