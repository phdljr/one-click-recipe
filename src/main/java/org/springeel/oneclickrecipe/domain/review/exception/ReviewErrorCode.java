package org.springeel.oneclickrecipe.domain.review.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

    //400
    ALREADY_EXIST_REVIEW(HttpStatus.BAD_REQUEST, "이미 후기가 존재합니다."),

    //403
    FORBIDDEN_ACCESS_REVIEW(HttpStatus.FORBIDDEN, "후기에 접근할 수 없습니다."),

    //404
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "후기를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
