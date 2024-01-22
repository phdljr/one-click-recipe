package org.springeel.oneclickrecipe.domain.food.dto.controller;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodUpdateControllerRequestDto(
    String name,
    Integer price,
    UnitType unit
) {

}
