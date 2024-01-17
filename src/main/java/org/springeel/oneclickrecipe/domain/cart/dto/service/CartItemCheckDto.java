package org.springeel.oneclickrecipe.domain.cart.dto.service;

import lombok.Builder;

@Builder
public record CartItemCheckDto(
    Long foodId,
    String name,
    int quantity,
    double price
) {

}
