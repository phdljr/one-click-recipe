package org.springeel.oneclickrecipe.domain.food.dto.service;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodUpdateServiceRequestDto(
    String name,
    Integer price,
    UnitType unit
) {

}
