package org.springeel.oneclickrecipe.domain.orderdetail.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessOrderDetailException extends CustomException {

    public ForbiddenAccessOrderDetailException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
