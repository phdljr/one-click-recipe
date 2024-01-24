package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

public record RecipeReadResponseDto(
    Long id,
    String title,
    String intro,
    Byte serving,
    Short time,
    String videoUrl,
    String imageUrl,
    String writer
) {

}
