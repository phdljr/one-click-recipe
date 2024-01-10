package org.springeel.oneclickrecipe.domain.order.dto.service;

import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;

public record OrderCreateResponseDto(
    Long orderId,
    OrderStatus orderStatus
) {

}
