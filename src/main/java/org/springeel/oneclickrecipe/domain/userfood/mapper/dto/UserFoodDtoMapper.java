package org.springeel.oneclickrecipe.domain.userfood.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.userfood.dto.controller.UserFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.controller.UserFoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)

public interface UserFoodDtoMapper {

    UserFoodCreateServiceRequestDto toFoodCreateServiceDto(
        UserFoodCreateControllerRequestDto controllerDto);

    UserFoodUpdateServiceRequestDto toFoodUpdateServiceDto(
        UserFoodUpdateControllerRequestDto controllerDto);

}
