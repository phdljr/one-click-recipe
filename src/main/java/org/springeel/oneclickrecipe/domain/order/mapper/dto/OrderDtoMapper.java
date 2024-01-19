package org.springeel.oneclickrecipe.domain.order.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface OrderDtoMapper {

    OrderCreateServiceRequestDto toOrderServiceRequestDto(
        OrderCreateControllerRequestDto controllerRequestDto
    );
}