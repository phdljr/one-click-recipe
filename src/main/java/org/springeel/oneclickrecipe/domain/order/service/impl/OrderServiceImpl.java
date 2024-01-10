package org.springeel.oneclickrecipe.domain.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.mapper.OrderMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.domain.user.UserRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Order createOrder(OrderCreateServiceRequestDto serviceRequestDto) {

        User user = userRepository.findById(serviceRequestDto.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다." + serviceRequestDto.userId()));

        Order order = new Order(
            serviceRequestDto.receiverName(),
            serviceRequestDto.receiverPhoneNumber(),
            serviceRequestDto.senderName(),
            serviceRequestDto.senderPhoneNumber(),
            serviceRequestDto.address(),
            serviceRequestDto.addressDetail(),
            serviceRequestDto.requirement(),
            serviceRequestDto.totalPrice(),
            OrderStatus.WAITING,
            user
        );

        return orderRepository.save(order);
    }
}