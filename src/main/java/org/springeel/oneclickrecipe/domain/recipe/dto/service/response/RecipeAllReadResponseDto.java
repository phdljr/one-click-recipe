package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

import lombok.Builder;

@Builder
public record RecipeAllReadResponseDto(
    Long id,
    String title,
    String intro,
    Byte serving,
    String imageUrl,
    String writer,
    Boolean isLiked,
    Integer likeCount
) {

}
