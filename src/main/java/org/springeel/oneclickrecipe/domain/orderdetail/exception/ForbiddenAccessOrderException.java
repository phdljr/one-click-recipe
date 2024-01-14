package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessOrderException extends CustomException {

    public ForbiddenAccessOrderException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
