package org.springeel.oneclickrecipe.domain.order.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    @Mapping(target = "status", expression = "java(OrderStatus.WAITING)")
    Order toEntity(OrderCreateServiceRequestDto dto, User user);

    /**
     * @Mapping를 사용한 이유는 한 객체의 필드를 다른 객체의 필드로 매핑하기
     * 위함 source는 원본 객체의 필드 이름, target은 대상 객체의 필드 이름
     */
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "status", target = "orderStatus")
    OrderCreateResponseDto toResponseDto(Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "status", target = "orderStatus")
    @Mapping(target = "orderDetails", source = "orderDetails")
    OrderReadResponseDto toOrderReadResponseDto(Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "status", target = "orderStatus")
    OrderReadAllResponseDto toOrderReadAllResponseDto(Order order);
}