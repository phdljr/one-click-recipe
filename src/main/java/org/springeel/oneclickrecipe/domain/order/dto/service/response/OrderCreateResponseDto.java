package org.springeel.oneclickrecipe.domain.order.dto.service.response;

import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;

public record OrderCreateResponseDto(
    Long id,
    OrderStatus orderStatus
) {

}
