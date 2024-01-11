package org.springeel.oneclickrecipe.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springeel.oneclickrecipe.global.exception.dto.CustomExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponseDto> customExceptionHandler(
        CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        log.error("커스텀 예외 발생 {} {}: {}", exception.getClass().getSimpleName(), errorCode.name(),
            errorCode.getMessage());
        return ResponseEntity
            .status(errorCode.getStatus())
            .body(CustomExceptionResponseDto.builder()
                .status(errorCode.getStatus().value())
                .name(errorCode.name())
                .message(errorCode.getMessage())
                .build());
    }
}
