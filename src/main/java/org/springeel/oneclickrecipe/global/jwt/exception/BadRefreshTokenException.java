package org.springeel.oneclickrecipe.global.jwt.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class BadRefreshTokenException extends CustomException {

    public BadRefreshTokenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
