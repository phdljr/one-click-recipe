package org.springeel.oneclickrecipe.domain.userfood.dto.service.request;

import org.springeel.oneclickrecipe.domain.userfood.entity.UnitType;

public record UserFoodUpdateServiceRequestDto(
    String name,
    UnitType unit
) {

}
