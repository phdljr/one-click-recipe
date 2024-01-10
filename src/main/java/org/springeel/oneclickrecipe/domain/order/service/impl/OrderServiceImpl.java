package org.springeel.oneclickrecipe.domain.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper; // Mapper 추가

    @Override
    public OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto,
        User user) {

        Order order = orderEntityMapper.toEntity(serviceRequestDto, user); // Mapper를 이용한 객체 생성
        order = orderRepository.save(order);

        return orderEntityMapper.toResponseDto(order);
    }
}