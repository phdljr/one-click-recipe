package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessUpdateServiceRequestDto(
    Byte sequence,
    String description,
    String imageUrl,
    Short time
) {

}
