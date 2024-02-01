package org.springeel.oneclickrecipe.domain.recipefood.dto.service.response;

import lombok.Builder;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

@Builder
public record RecipeFoodReadResponseDto(
    Long id,
    String foodName,
    Short amount,
    Integer price,
    UnitType unit
) {

}
