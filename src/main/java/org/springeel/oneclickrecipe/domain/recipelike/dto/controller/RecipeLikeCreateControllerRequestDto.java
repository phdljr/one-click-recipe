package org.springeel.oneclickrecipe.domain.recipelike.dto.controller;

public record RecipeLikeCreateControllerRequestDto(

    Long recipeId,
    Long userId
) {
}
