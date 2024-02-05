package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundRecipeProcessException extends CustomException {

    public NotFoundRecipeProcessException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
