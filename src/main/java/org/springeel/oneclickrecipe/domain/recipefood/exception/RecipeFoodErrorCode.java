package org.springeel.oneclickrecipe.domain.recipefood.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecipeFoodErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_RECIPEFOOD(HttpStatus.BAD_REQUEST, "이미 레시피_식재료가 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_RECIPEFOOD(HttpStatus.FORBIDDEN, "레시피_식재료에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_RECIPEFOOD(HttpStatus.NOT_FOUND, "레시피_식재료를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
