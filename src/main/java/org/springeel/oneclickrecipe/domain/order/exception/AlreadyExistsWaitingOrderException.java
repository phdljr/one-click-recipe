package org.springeel.oneclickrecipe.domain.order.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsWaitingOrderException extends CustomException {

    public AlreadyExistsWaitingOrderException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
