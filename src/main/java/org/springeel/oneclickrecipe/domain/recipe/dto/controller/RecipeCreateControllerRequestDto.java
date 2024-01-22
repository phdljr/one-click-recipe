package org.springeel.oneclickrecipe.domain.recipe.dto.controller;

public record RecipeCreateControllerRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoUrl
) {

}
