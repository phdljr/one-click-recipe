package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecipeErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_RECIPE(HttpStatus.BAD_REQUEST, "이미 레시피가 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_RECIPE(HttpStatus.FORBIDDEN, "레시피에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_RECIPE(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
