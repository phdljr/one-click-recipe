package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessReadServiceResponseDto(
    Byte sequence,
    String description,
    Short time
) {

}
