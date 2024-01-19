package org.springeel.oneclickrecipe.domain.review.dto.service.response;

import lombok.Builder;

@Builder
public record ReviewReadResponseDto(
    Long id,
    String content,
    Byte star
) {

}
