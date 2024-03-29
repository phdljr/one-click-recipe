package org.springeel.oneclickrecipe.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    // 400
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    FORBIDDEN_CHANGE_NICKNAME(HttpStatus.BAD_REQUEST, "변경할 수 없는 닉네임입니다."),
    NOT_MATCH_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "새 비밀번호를 다시 확인해주세요."),

    // 404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    BAD_ROLE(HttpStatus.BAD_REQUEST, "사용자에 권한이 없습니다."),
    BAD_LOGIN(HttpStatus.NOT_FOUND, "로그인에 실패하였습니다.");

    private final HttpStatus status;
    private final String message;
}
