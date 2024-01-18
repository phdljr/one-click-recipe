package org.springeel.oneclickrecipe.domain.recipe.dto.service;

public record RecipeAllReadResponseDto(
    String title,
    String intro,
    Byte serving
) {

}
