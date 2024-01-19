package org.springeel.oneclickrecipe.domain.recipefood.dto.service.response;

import lombok.Builder;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

@Builder
public record RecipeFoodReadResponseDto(
    String name,
    Short amount,
    Integer price,
    UnitType unit
) {

}
