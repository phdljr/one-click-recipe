package org.springeel.oneclickrecipe.domain.order.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderDtoMapper {
    OrderCreateServiceRequestDto toOrderServiceRequestDto(
        OrderCreateControllerRequestDto createControllerRequestDto
    );
}