package org.springeel.oneclickrecipe.global.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {

    // 401
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,  "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

    private final HttpStatus status;
    private final String message;
}