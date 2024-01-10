package org.springeel.oneclickrecipe.domain.order.mapper.entity;

import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface OrderEntityMapper {

    Order toEntity(OrderCreateServiceRequestDto dto, User user);
}
