package org.springeel.oneclickrecipe.domain.userfood.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundUserFoodException extends CustomException {

    public NotFoundUserFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
