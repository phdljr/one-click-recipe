package org.springeel.oneclickrecipe.domain.food.dto.service;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodCreateServiceRequestDto(
    String foodName,
    Integer price,
    UnitType unitType
) {

}
