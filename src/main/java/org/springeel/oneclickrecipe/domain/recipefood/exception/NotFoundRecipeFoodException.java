package org.springeel.oneclickrecipe.domain.recipefood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundRecipeFoodException extends CustomException {

    public NotFoundRecipeFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
