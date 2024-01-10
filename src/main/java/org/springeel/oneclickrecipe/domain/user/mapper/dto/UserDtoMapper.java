package org.springeel.oneclickrecipe.domain.user.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserCreateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface UserDtoMapper {

    UserCreateServiceRequestDto toServiceRequestDto(
        UserCreateControllerRequestDto controllerRequestDto);
}
