package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

public record RecipeAllReadResponseDto(
    String title,
    String intro,
    Byte serving,
    String imageUrl
) {

}
