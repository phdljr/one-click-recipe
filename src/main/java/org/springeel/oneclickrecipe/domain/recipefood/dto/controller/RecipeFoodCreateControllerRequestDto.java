package org.springeel.oneclickrecipe.domain.recipefood.dto.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record RecipeFoodCreateControllerRequestDto(
    @NotEmpty(message = "식재료를 입력해 주세요.")
    String foodName,

    @Min(value = 1, message = "수량을 입력해 주세요.")
    Short amount
) {

}
