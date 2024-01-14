package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderDetailErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_ORDER_DETAIL(HttpStatus.BAD_REQUEST, "이미 주문 상세 내역이 존재합니다."),

    // 403
    FORBIDDEN_ACCESS_ORDER_DETAIL(HttpStatus.FORBIDDEN, "주문 상세 내역에 접근할 수 없습니다."),

    // 404
    NOT_FOUND_ORDER_DETAIL(HttpStatus.NOT_FOUND, "주문 상세 내역를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
