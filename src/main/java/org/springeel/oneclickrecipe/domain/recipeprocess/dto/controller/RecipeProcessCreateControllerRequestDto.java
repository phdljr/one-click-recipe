package org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller;

import jakarta.validation.constraints.NotEmpty;

public record RecipeProcessCreateControllerRequestDto(
    Byte sequence,

    @NotEmpty(message = "조리 설명을 입력해 주세요.")
    String description
) {

}
