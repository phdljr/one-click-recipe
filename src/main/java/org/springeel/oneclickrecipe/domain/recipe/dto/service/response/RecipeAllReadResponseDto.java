package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

public record RecipeAllReadResponseDto(
    Long id,
    String title,
    String intro,
    Byte serving,
    String imageUrl,
    String writer
) {

}
