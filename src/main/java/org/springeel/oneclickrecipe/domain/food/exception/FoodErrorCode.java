package org.springeel.oneclickrecipe.domain.food.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_FOOD(HttpStatus.BAD_REQUEST, "이미 식재료가 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_FOOD(HttpStatus.FORBIDDEN, "식재료에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_FOOD(HttpStatus.NOT_FOUND, "식재료를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
