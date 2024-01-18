package org.springeel.oneclickrecipe.global.exception.dto;

import lombok.Builder;

@Builder
public record FieldErrorResponseDto(
    String name,
    String message
) {

}
