package org.springeel.oneclickrecipe.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.mapper.OrderMapper;
import org.springeel.oneclickrecipe.domain.order.service.impl.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateControllerRequestDto requestDto
    ) {
        Long userId = 1L;
        Order order = orderService.createOrder(orderMapper.toServiceDto(requestDto, userId));
        OrderCreateResponseDto responseDto = orderMapper.toControllerResponseDto(order);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
