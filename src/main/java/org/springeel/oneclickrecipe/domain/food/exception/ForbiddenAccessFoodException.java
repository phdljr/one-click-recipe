package org.springeel.oneclickrecipe.domain.food.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessFoodException extends CustomException {

    public ForbiddenAccessFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
