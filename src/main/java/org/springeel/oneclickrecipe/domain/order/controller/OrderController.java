package org.springeel.oneclickrecipe.domain.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.dto.controller.OrderCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.mapper.dto.OrderDtoMapper;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderDtoMapper orderDtoMapper;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        OrderCreateServiceRequestDto serviceRequestDto = orderDtoMapper.toOrderServiceRequestDto(
            controllerRequestDto);
        OrderCreateResponseDto responseDto = orderService.createOrder(serviceRequestDto,
            userDetails.user());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderReadAllResponseDto>> getAllUserOrders(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<OrderReadAllResponseDto> orderReadAllResponseDtoList = orderService.getAllUserOrders(
            page, userDetails.user());
        return ResponseEntity.ok(orderReadAllResponseDtoList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadResponseDto> getOrderById(
        @PathVariable(name = "orderId") Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        OrderReadResponseDto readResponseDto = orderService.getOrderById(orderId,
            userDetails.user());
        return ResponseEntity.ok(readResponseDto);
    }

    @GetMapping("/waiting")
    public ResponseEntity<Void> checkWaitingOrder(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        orderService.checkWaitingOrder(userDetails.user());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(
        @PathVariable(name = "orderId") Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        orderService.deleteOrder(orderId, userDetails.user());
        return ResponseEntity.ok().build();
    }
}