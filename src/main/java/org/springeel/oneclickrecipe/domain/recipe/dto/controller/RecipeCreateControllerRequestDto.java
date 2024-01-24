package org.springeel.oneclickrecipe.domain.recipe.dto.controller;

public record RecipeCreateControllerRequestDto(
    String title,
    String intro,
    Byte serving,
    Short time,
    String videoUrl
) {

}
