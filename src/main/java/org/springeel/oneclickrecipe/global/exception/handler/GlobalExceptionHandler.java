package org.springeel.oneclickrecipe.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public void customExceptionHandler(CustomException exception){
        ErrorCode errorCode = exception.getErrorCode();
        log.error("커스텀 예외 발생 {}: {}", errorCode.name(), errorCode.getMessage());
    }
}
