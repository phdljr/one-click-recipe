package org.springeel.oneclickrecipe.domain.order.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.user.UserRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order createOrder(OrderCreateServiceRequestDto dto) {

        User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다." + dto.userId()));

        Order order = new Order(
            dto.receiverName(),
            dto.receiverPhoneNumber(),
            dto.senderName(),
            dto.senderPhoneNumber(),
            dto.address(),
            dto.addressDetail(),
            dto.requirement(),
            dto.totalPrice(),
            OrderStatus.WAITING,
            user
        );

        return orderRepository.save(order);
    }
}