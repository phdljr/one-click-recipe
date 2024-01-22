package org.springeel.oneclickrecipe.domain.cart.dto.service.response;

import lombok.Builder;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

@Builder
public record CartReadResponseDto(
    Long id,
    String name,
    Short quantity,
    UnitType unit,
    Integer price
) {

}
