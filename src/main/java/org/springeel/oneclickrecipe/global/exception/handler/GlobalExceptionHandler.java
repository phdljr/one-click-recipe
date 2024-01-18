package org.springeel.oneclickrecipe.global.exception.handler;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springeel.oneclickrecipe.global.exception.dto.BeanValidationExceptionResponseDto;
import org.springeel.oneclickrecipe.global.exception.dto.CustomExceptionResponseDto;
import org.springeel.oneclickrecipe.global.exception.dto.FieldErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(
        BindingResult bindingResult) {
        List<FieldErrorResponseDto> fieldErrorResponseDtos = bindingResult.getFieldErrors().stream()
            .map(fieldError -> FieldErrorResponseDto.builder()
                .name(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build())
            .toList();

        log.error("Bean Validation 예외 발생: {}", fieldErrorResponseDtos);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(BeanValidationExceptionResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .messages(fieldErrorResponseDtos)
                .build());
    }
}
