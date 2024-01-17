package org.springeel.oneclickrecipe.domain.cart.dto.service;

public record CartItemCheckDto(
    Long foodId,
    String name,
    int quantity,
    double price
) {

}
