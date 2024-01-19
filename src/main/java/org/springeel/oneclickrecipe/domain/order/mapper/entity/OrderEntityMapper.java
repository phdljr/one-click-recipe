package org.springeel.oneclickrecipe.domain.order.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity.OrderDetailEntityMapper;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(
    componentModel = SPRING,
    uses = {OrderDetailEntityMapper.class}
)
public interface OrderEntityMapper {

    @Mapping(target = "status", expression = "java(OrderStatus.WAITING)")
    Order toOrder(OrderCreateServiceRequestDto dto, User user, Integer totalPrice);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "orderStatus")
    OrderCreateResponseDto toOrderCreateResponseDto(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "orderStatus")
    @Mapping(target = "orderDetails", source = "orderDetails")
    OrderReadResponseDto toOrderReadResponseDto(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "orderStatus")
    OrderReadAllResponseDto toOrderReadAllResponseDto(Order order);
}