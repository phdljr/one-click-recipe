package org.springeel.oneclickrecipe.domain.food.dto;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodReadResponseDto(
    Long id,
    String name,
    Float price,
    UnitType unit

) {

}
