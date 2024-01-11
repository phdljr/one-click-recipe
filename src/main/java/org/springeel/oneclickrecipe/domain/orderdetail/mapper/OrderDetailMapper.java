package org.springeel.oneclickrecipe.domain.orderdetail.mapper;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.OrderDetailDto;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);
}
