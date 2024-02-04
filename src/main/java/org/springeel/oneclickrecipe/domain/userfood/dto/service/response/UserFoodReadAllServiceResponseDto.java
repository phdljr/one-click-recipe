package org.springeel.oneclickrecipe.domain.userfood.dto.service.response;

import org.springeel.oneclickrecipe.domain.userfood.entity.UnitType;

public record UserFoodReadAllServiceResponseDto(
    Long id,
    String name,
    UnitType unit
) {

}
