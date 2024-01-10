package org.springeel.oneclickrecipe.domain.order.service;

import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto, User user);

}
