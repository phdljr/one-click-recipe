package org.springeel.oneclickrecipe.domain.orderdetail.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.controller.OrderDetailCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.service.OrderDetailCreateResponseDto;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.dto.OrderDetailDtoMapper;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity.OrderDetailEntityMapper;
import org.springeel.oneclickrecipe.domain.orderdetail.repository.OrderDetailRepository;
import org.springeel.oneclickrecipe.domain.orderdetail.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailDtoMapper orderDetailDtoMapper;
    private final OrderDetailEntityMapper orderDetailEntityMapper;

    @Override
    @Transactional
    public OrderDetailCreateResponseDto createOrderDetail(Long userId, Long orderId,
        OrderDetailCreateControllerRequestDto orderDetailCreateControllerRequestDto) {

        // 특정 Order 조회
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
            .orElseThrow(() -> new EntityNotFoundException(
                "Order not found or does not belong to the user"));

        // Dto를 Entity로 변환
        OrderDetail orderDetail = orderDetailDtoMapper.toEntity(
            orderDetailCreateControllerRequestDto);

        // Order 연결
        orderDetail.setOrder(order);

        // OrderDetail 저장
        orderDetail  = orderDetailRepository.save(orderDetail);

        // Entity를 Dto로 변환하여 반환
        return orderDetailEntityMapper.toDto(orderDetail);
    }
}
