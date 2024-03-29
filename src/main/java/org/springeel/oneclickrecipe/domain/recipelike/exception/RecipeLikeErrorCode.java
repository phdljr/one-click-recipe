package org.springeel.oneclickrecipe.domain.recipelike.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecipeLikeErrorCode implements ErrorCode {

    //400
    ALREADY_EXIST_RECIPELIKE(HttpStatus.BAD_REQUEST, "이미 좋아요가 존재합니다."),

    //404
    NOT_FOUND_RECIPELIKE(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String message;
}
