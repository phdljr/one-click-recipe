package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotMatchPasswordException extends CustomException {

    public NotMatchPasswordException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
