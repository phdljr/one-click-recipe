package org.springeel.oneclickrecipe.domain.cart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CartErrorCode implements ErrorCode {

    // 400

    // 403

    // 404
    NOT_FOUND_CART_ITEM(HttpStatus.NOT_FOUND, "장바구니에 물품이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}