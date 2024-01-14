package org.springeel.oneclickrecipe.domain.orderdetail.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.controller.OrderDetailCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderDetailDtoMapper {

    OrderDetail toEntity(OrderDetailCreateControllerRequestDto dto);
}