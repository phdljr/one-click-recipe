package org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller;

public record RecipeProcessUpdateControllerRequestDto(
    Byte sequence,
    String description,
    Short time
) {

}
