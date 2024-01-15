package org.springeel.oneclickrecipe.domain.cart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CartErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_CART(HttpStatus.BAD_REQUEST, "이미 장바구니이 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_CART(HttpStatus.FORBIDDEN, "장바구니에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_CART(HttpStatus.NOT_FOUND, "장바구니를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
