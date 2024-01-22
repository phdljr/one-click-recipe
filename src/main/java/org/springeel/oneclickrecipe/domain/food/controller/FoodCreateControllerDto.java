package org.springeel.oneclickrecipe.domain.food.controller;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodCreateControllerDto(
    String foodName,
    Integer price,
    UnitType unitType
) {

}
