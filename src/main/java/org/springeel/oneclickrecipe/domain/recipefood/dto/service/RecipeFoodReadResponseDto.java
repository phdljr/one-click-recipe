package org.springeel.oneclickrecipe.domain.recipefood.dto.service;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record RecipeFoodReadResponseDto(
    String name,
    String amount,
    Float price,
    UnitType unit
) {

}
