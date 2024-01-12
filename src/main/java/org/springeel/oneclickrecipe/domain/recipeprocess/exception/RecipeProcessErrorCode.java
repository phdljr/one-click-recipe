package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecipeProcessErrorCode implements ErrorCode {

    // 400
    // 403
    // 404
    NOT_FOUND_RECIPE_PROCESS(HttpStatus.NOT_FOUND, "조리과정을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
