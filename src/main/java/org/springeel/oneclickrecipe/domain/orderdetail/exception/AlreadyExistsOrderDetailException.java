package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsOrderDetailException extends CustomException {

    public AlreadyExistsOrderDetailException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
