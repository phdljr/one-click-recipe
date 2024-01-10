package org.springeel.oneclickrecipe.domain.order.service;

import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;

public interface OrderService {

    Order createOrder(OrderCreateServiceRequestDto serviceRequestDto);

}
