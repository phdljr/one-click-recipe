package org.springeel.oneclickrecipe.domain.recipe.dto.service.request;

public record RecipeUpdateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoUrl,
    String imageUrl
) {

}
