package org.springeel.oneclickrecipe.domain.cart.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessCartException extends CustomException {

    public ForbiddenAccessCartException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
