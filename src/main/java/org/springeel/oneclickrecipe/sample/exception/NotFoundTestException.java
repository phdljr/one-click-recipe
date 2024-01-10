package org.springeel.oneclickrecipe.sample.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundTestException extends CustomException {

    public NotFoundTestException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
