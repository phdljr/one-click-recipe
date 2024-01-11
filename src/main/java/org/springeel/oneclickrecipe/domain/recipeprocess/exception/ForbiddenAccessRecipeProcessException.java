package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessRecipeProcessException extends CustomException {

    public ForbiddenAccessRecipeProcessException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
