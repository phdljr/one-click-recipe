package org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller;

public record RecipeProcessUpdateControllerRequestDto(
    String sequence,
    String description,
    String imageUrl,
    Short time
) {

}
