package org.springeel.oneclickrecipe.domain.cart.dto.controller;

import java.util.List;

public record CartAddControllerRequestDto(
    List<Long> recipeFoodIds
) {

}
