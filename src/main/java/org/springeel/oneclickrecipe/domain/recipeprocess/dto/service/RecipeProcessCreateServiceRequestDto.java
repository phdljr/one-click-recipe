package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

public record RecipeProcessCreateServiceRequestDto(
    String sequence,
    String description,
    String imageUrl
) {

}
