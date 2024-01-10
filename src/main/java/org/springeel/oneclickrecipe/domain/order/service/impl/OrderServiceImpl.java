package org.springeel.oneclickrecipe.domain.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
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
    private final OrderEntityMapper orderEntityMapper; // Mapper 추가
    @Override
    public Order createOrder(OrderCreateServiceRequestDto serviceRequestDto) {

        User user = userRepository.findById(serviceRequestDto.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다." + serviceRequestDto.userId()));

        Order order = orderEntityMapper.toEntity(serviceRequestDto, user); // Mapper를 이용한 객체 생성

        return orderRepository.save(order);
    }
}