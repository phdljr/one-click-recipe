package org.springeel.oneclickrecipe.domain.review.dto.service;

import lombok.Builder;

@Builder
public record ReviewReadResponseDto(
    String content,
    Byte star
) {

}
