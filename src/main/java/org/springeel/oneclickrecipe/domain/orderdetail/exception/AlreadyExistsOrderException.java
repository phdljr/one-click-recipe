package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsOrderException extends CustomException {

    public AlreadyExistsOrderException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
