package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request;

public record RecipeProcessUpdateServiceRequestDto(
    Byte sequence,
    String description,
    Short time
) {

}
