package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_ORDER(HttpStatus.BAD_REQUEST, "이미 주문내역이 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_ORDER(HttpStatus.FORBIDDEN, "주문내역에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "주문내역를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
