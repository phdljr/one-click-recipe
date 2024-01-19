package org.springeel.oneclickrecipe.domain.review.dto.service.request;

public record ReviewUpdateServiceRequestDto(
    String content,
    Byte star
) {

}

