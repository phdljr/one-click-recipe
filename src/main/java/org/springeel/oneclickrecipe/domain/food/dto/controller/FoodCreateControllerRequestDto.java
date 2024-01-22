package org.springeel.oneclickrecipe.domain.food.dto.controller;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodCreateControllerRequestDto(
    String foodName,
    Integer price,
    UnitType unitType
) {

}
