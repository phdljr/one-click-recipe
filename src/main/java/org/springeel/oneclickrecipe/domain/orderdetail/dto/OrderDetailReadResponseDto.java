package org.springeel.oneclickrecipe.domain.orderdetail.dto;

import org.springeel.oneclickrecipe.domain.food.entity.UnitType;

public record OrderDetailReadResponseDto(
    Long id,
    String name,
    Short amount,
    UnitType unit,
    Integer price
) {

}