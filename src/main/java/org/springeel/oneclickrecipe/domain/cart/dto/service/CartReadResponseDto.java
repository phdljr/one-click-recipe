package org.springeel.oneclickrecipe.domain.cart.dto.service;

import lombok.Builder;

@Builder
public record CartReadResponseDto(
    Long id,
    String name,
    Short quantity,
    Integer price
) {

}
