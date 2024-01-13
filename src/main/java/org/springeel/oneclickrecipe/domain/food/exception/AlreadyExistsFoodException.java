package org.springeel.oneclickrecipe.domain.food.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsFoodException extends CustomException {

    public AlreadyExistsFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
