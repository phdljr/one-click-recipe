package org.springeel.oneclickrecipe.domain.order.dto.service;

import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;

public record OrderReadResponseDto(
    Long orderId,
    String receiverName,
    String receiverPhoneNumber,
    String address,
    String addressDetail,
    Integer totalPrice,
    OrderStatus orderStatus
//    List<OrderDetailDto> orderDetails // 주문 상세 정보를 담을 리스트
) {

}