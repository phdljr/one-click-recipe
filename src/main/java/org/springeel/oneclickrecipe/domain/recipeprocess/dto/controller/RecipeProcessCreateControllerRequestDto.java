package org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller;

public record RecipeProcessCreateControllerRequestDto(
    Byte sequence,
    String description
) {

}
