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
) {
}