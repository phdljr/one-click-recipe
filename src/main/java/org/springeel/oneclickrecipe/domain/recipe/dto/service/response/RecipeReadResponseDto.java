package org.springeel.oneclickrecipe.domain.recipe.dto.service.response;

import lombok.Builder;

@Builder
public record RecipeReadResponseDto(
    Long id,
    String title,
    String intro,
    Byte serving,
    String videoUrl,
    String imageUrl,
    String writer,
    Boolean follow,
    Long writerId
) {

}
