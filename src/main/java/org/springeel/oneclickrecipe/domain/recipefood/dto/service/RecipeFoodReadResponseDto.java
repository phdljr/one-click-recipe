package org.springeel.oneclickrecipe.domain.recipefood.dto.service;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record RecipeFoodReadResponseDto(
    String 성,
    String amount,
    Float price,
    UnitType unit
) {

}
