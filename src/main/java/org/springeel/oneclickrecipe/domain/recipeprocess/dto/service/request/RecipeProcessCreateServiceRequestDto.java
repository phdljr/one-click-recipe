package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request;

public record RecipeProcessCreateServiceRequestDto(
    Byte sequence,
    String description,
    Short time
) {

}
