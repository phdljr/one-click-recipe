package org.springeel.oneclickrecipe.domain.cart.dto.service.response;

import java.util.List;
import lombok.Builder;

@Builder
public record CartReadAllResponseDto(
    Integer totalPrice,
    List<CartReadResponseDto> foods
) {

}
