package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.response;

public record RecipeProcessReadResponseDto(
    Long id,
    Byte sequence,
    String description,
    Short time,
    String imageUrl
) {

}
