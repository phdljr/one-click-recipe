package org.springeel.oneclickrecipe.domain.orderdetail.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.controller.OrderDetailCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.service.OrderDetailCreateResponseDto;
import org.springeel.oneclickrecipe.domain.orderdetail.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/order-details")
@RestController
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<OrderDetailCreateResponseDto> createOrderDetail(
        @RequestBody OrderDetailCreateControllerRequestDto orderDetailCreateControllerRequestDto
    ) {
        OrderDetailCreateResponseDto orderDetailCreateResponseDto = orderDetailService.createOrderDetail(
            orderDetailCreateControllerRequestDto);
        return new ResponseEntity<>(orderDetailCreateResponseDto, HttpStatus.CREATED);
    }

}
