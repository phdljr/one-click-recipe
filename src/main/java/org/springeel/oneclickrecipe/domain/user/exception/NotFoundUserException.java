package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundUserException extends CustomException {

    public NotFoundUserException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
