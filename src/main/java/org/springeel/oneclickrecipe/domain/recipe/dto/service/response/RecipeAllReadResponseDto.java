package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

import org.springeel.oneclickrecipe.domain.user.entity.User;

public record RecipeAllReadResponseDto(
    String title,
    String intro,
    Byte serving,
    String imageUrl,

    String writer
) {

}
