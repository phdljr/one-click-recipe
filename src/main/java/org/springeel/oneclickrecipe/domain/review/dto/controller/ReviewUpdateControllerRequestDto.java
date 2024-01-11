package org.springeel.oneclickrecipe.domain.review.dto.controller;

public record ReviewUpdateControllerRequestDto(
    String content,
    Byte star
) {
}
