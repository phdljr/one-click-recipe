package org.springeel.oneclickrecipe.domain.cart.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsCartException extends CustomException {

    public AlreadyExistsCartException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
