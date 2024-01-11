package org.springeel.oneclickrecipe.domain.recipe.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessRecipeException extends CustomException {

    public ForbiddenAccessRecipeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
