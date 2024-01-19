package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

public record RecipeReadResponseDto(
    String title,
    String intro,
    String videoUrl,
    String imageUrl,
    String writer
) {

}
