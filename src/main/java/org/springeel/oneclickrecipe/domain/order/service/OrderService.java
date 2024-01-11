package org.springeel.oneclickrecipe.domain.order.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto, User user);

    List<OrderReadResponseDto> getUserOrders(Long userId);

    OrderReadResponseDto getOrderById(Long orderId, Long userId);

    List<OrderReadAllResponseDto> getAllUserOrders(Long userId);
}