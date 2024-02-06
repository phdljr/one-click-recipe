package org.springeel.oneclickrecipe.domain.userfood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsUserFoodException extends CustomException {

    public AlreadyExistsUserFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
