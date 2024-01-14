package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundOrderDetailException extends CustomException {

    public NotFoundOrderDetailException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
