package org.springeel.oneclickrecipe.domain.userfood.dto.controller;

import org.springeel.oneclickrecipe.domain.userfood.entity.UnitType;

public record UserFoodUpdateControllerRequestDto(
    String name,
    UnitType unit
) {

}
