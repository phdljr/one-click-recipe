package org.springeel.oneclickrecipe.domain.order.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto, User user);

    OrderReadResponseDto getOrderById(Long orderId, User user);

    List<OrderReadAllResponseDto> getAllUserOrders(User user);

    void deleteOrder(Long orderId, User user);

    void checkWaitingOrder(User user);
}