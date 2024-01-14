package org.springeel.oneclickrecipe.domain.recipefood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsRecipeFoodException extends CustomException {

    public AlreadyExistsRecipeFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
