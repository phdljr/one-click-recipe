package org.springeel.oneclickrecipe.domain.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderCreateServiceRequestDto toServiceDto(OrderCreateControllerRequestDto controllerRequestDto,
        Long userId);

    OrderCreateResponseDto toControllerResponseDto(Order order);

}
