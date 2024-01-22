package org.springeel.oneclickrecipe.domain.food.dto;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodCreateServiceDto(
    String foodName,
    Integer price,
    UnitType unitType
) {

}
