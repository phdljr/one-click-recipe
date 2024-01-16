package org.springeel.oneclickrecipe.domain.cart.dto.service;

public record CartAddServiceRequestDto(
    Long userId,
    Long recipeFoodId
) {

}
