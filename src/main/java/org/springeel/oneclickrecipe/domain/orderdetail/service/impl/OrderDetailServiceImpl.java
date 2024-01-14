package org.springeel.oneclickrecipe.domain.orderdetail.service.impl;

import lombok.RequiredArgsConstructor;
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

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailDtoMapper orderDetailDtoMapper;
    private final OrderDetailEntityMapper orderDetailEntityMapper;

    @Override
    @Transactional
    public OrderDetailCreateResponseDto createOrderDetail(
        OrderDetailCreateControllerRequestDto orderDetailCreateControllerRequestDto) {
        OrderDetail orderDetail = orderDetailDtoMapper.toEntity(
            orderDetailCreateControllerRequestDto);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailEntityMapper.toDto(savedOrderDetail);
    }
}
