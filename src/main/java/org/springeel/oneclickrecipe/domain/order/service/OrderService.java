package org.springeel.oneclickrecipe.domain.order.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.user.UserRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto, User user);

    List<OrderReadResponseDto> getUserOrders(Long userId);
}