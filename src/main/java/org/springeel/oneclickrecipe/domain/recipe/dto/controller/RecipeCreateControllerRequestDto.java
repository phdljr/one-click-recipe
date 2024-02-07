package org.springeel.oneclickrecipe.domain.recipe.dto.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipeCreateControllerRequestDto(
    @NotBlank(message = "레시피 제목을 입력해주세요.")
    String title,

    String intro,

    @NotNull(message = "인분을 입력해주세요.")
    Byte serving,

    String videoUrl
) {

}
