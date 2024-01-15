package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessUpdateServiceRequestDto(
    String sequence,
    String description,
    String imageUrl,
    Short time
) {

}
