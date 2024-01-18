package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessReadResponseDto(
    Byte sequence,
    String description,
    Short time
) {

}
