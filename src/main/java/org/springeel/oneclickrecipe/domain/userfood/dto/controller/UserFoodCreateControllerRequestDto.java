package org.springeel.oneclickrecipe.domain.userfood.dto.controller;

import org.springeel.oneclickrecipe.domain.userfood.entity.UnitType;

public record UserFoodCreateControllerRequestDto(
    String name,
    UnitType unit
) {

}
