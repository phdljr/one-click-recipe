package org.springeel.oneclickrecipe.domain.orderdetail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.OrderDetailDto;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "id", target = "detailId")
    @Mapping(source = "foodName", target = "name")
    @Mapping(source = "amount", target = "quantity")
    @Mapping(source = "price", target = "unitPrice")
    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);
}