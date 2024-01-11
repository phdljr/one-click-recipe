package org.springeel.oneclickrecipe.domain.recipe.dto.service;

public record RecipeUpdateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoPath
) {

}
