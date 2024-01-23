package org.springeel.oneclickrecipe.domain.food.dto.service.response;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record FoodReadAllServiceResponseDto(
    Long id,
    String name,
    Integer price,
    UnitType unit
) {

}
