package org.springeel.oneclickrecipe.domain.cart.dto.service;

import java.util.List;
import lombok.Builder;

@Builder
public record CartCheckResponseDto(
    double totalPrice,
    List<CartItemCheckDto> items
) {

}
