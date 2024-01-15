package org.springeel.oneclickrecipe.domain.cart.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundCartException extends CustomException {

    public NotFoundCartException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
