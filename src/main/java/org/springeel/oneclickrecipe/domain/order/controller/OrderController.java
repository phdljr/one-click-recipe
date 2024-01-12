package org.springeel.oneclickrecipe.domain.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.mapper.dto.OrderDtoMapper;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/orders")
@RestController
public class OrderController {

    private final OrderDtoMapper orderDtoMapper;
    private final OrderService orderService;

    // 주문 생성
    @PostMapping
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

    // 주문 내역 목록 조회
    @GetMapping
    public ResponseEntity<List<OrderReadAllResponseDto>> getAllUserOrders(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        List<OrderReadAllResponseDto> orderReadAllResponseDtoList = orderService.getAllUserOrders(
            userDetailsImpl.user()
                .getId());
        return ResponseEntity.ok(orderReadAllResponseDtoList);
    }

    // 주문 내역 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadResponseDto> getOrderById(
        @PathVariable(name = "orderId") Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        OrderReadResponseDto readResponseDto = orderService.getOrderById(orderId,
            userDetailsImpl.user().getId());
        return ResponseEntity.ok(readResponseDto);
    }
}