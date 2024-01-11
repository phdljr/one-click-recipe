package org.springeel.oneclickrecipe.domain.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.mapper.dto.OrderDtoMapper;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class OrderController {

    private final OrderDtoMapper orderDtoMapper;
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateControllerRequestDto createControllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        OrderCreateServiceRequestDto serviceRequestDto = orderDtoMapper.toOrderServiceRequestDto(
            createControllerRequestDto);
        OrderCreateResponseDto responseDto = orderService.createOrder(serviceRequestDto,
            userDetailsImpl.user());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderReadResponseDto>> getUserOrders(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        List<OrderReadResponseDto> orderList = orderService.getUserOrders(userDetailsImpl.user()
            .getId());
        return ResponseEntity.ok(orderList);
    }
}