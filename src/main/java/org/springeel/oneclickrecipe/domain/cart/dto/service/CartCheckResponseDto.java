package org.springeel.oneclickrecipe.domain.cart.dto.service;

import java.util.List;

public record CartCheckResponseDto(
    double totalPrice,
    List<CartItemCheckDto> items
) {

}
