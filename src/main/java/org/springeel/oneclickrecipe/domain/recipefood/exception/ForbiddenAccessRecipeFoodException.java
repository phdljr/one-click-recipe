package org.springeel.oneclickrecipe.domain.recipefood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessRecipeFoodException extends CustomException {

    public ForbiddenAccessRecipeFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
