package org.springeel.oneclickrecipe.domain.userfood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessUserFoodException extends CustomException {

    public ForbiddenAccessUserFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
