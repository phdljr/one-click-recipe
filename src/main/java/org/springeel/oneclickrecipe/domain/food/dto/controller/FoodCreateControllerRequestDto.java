package org.springeel.oneclickrecipe.domain.food.dto.controller;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodCreateControllerRequestDto(
    String name,
    Integer price,
    UnitType unit
) {

}
