package org.springeel.oneclickrecipe.domain.recipe.dto.service.request;

public record RecipeCreateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    Short time,
    String videoUrl
) {

}
