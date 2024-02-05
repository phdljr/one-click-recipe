package org.springeel.oneclickrecipe.domain.order.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundOrderException extends CustomException {

    public NotFoundOrderException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
