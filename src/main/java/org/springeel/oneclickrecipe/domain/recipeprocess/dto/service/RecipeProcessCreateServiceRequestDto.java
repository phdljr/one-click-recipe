package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessCreateServiceRequestDto(
    Byte sequence,
    String description,
    Short time
) {

}
