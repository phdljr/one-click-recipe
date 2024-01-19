package org.springeel.oneclickrecipe.domain.review.dto.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ReviewUpdateControllerRequestDto(
    @NotBlank
    String content,
    @Min(value = 0)
    @Max(value = 5)
    Byte star
) {

}
