package org.springeel.oneclickrecipe.domain.cart.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundCardItemException extends CustomException {

    public NotFoundCardItemException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
