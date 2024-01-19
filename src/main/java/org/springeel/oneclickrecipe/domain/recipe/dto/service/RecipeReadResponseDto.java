package org.springeel.oneclickrecipe.domain.recipe.dto.service;

public record RecipeReadResponseDto(
    String title,
    String intro,
    String videoPath,
    String imageUrl
) {

}
