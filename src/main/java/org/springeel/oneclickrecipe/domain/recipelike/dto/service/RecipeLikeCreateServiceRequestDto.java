package org.springeel.oneclickrecipe.domain.recipelike.dto.service;

public record RecipeLikeCreateServiceRequestDto(
    Long recipeId,
    Long userId
) {
}
