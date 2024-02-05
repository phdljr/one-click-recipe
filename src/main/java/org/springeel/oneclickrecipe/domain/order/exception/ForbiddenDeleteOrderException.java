package org.springeel.oneclickrecipe.domain.order.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenDeleteOrderException extends CustomException {

    public ForbiddenDeleteOrderException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
