package org.springeel.oneclickrecipe.domain.recipe.dto.service.request;

public record RecipeCreateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoUrl,
    String imageUrl
) {

}
