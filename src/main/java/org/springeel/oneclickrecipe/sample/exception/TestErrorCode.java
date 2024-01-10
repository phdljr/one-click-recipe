package org.springeel.oneclickrecipe.sample.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TestErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_TEST(HttpStatus.BAD_REQUEST, "이미 테스트가 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_TEST(HttpStatus.FORBIDDEN, "테스트에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_TEST(HttpStatus.NOT_FOUND, "테스트를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
