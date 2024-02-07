package org.springeel.oneclickrecipe.domain.review.dto.service.response;

public record ReviewCreateResponseDto(
    Long id,
    String content,
    Byte star,
    String writer
) {

}
