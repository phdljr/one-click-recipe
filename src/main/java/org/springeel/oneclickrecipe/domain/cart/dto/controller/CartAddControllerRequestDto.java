package org.springeel.oneclickrecipe.domain.cart.dto.controller;

import jakarta.validation.constraints.Size;
import java.util.List;

public record CartAddControllerRequestDto(
    @Size(min = 1)
    List<Long> recipeFoodIds
) {

}
