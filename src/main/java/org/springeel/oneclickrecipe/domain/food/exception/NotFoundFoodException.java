package org.springeel.oneclickrecipe.domain.food.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundFoodException extends CustomException {

    public NotFoundFoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
