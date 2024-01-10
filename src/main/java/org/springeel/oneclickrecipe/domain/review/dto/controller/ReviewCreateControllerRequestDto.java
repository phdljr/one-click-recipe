package org.springeel.oneclickrecipe.domain.review.dto.controller;

public record ReviewCreateControllerRequestDto(
    String content,
    Byte star
) {

}

