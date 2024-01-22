package org.springeel.oneclickrecipe.domain.review.dto.service.request;

public record ReviewCreateServiceRequestDto(
    String content,
    Byte star
) {

}

