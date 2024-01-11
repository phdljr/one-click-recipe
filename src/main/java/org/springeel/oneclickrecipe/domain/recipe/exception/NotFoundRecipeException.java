package org.springeel.oneclickrecipe.domain.recipe.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundRecipeException extends CustomException {

    public NotFoundRecipeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
