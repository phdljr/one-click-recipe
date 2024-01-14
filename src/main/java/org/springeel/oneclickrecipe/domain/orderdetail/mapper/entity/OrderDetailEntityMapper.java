package org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.service.OrderDetailCreateResponseDto;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderDetailEntityMapper {

    OrderDetailCreateResponseDto toDto(OrderDetail entity);
}
