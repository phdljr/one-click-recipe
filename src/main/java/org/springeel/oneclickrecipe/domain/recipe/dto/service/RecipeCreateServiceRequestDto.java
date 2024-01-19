package org.springeel.oneclickrecipe.domain.recipe.dto.service;

public record RecipeCreateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoPath
) {

}
