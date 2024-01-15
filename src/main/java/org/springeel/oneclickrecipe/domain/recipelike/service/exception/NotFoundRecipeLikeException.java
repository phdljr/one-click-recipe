package org.springeel.oneclickrecipe.domain.recipelike.service.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundRecipeLikeException extends CustomException {

    public NotFoundRecipeLikeException(final ErrorCode errorCode) {
        super(errorCode);
    }

}
