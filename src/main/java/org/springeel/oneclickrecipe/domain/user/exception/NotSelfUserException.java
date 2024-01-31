package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotSelfUserException extends CustomException {
    public NotSelfUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
