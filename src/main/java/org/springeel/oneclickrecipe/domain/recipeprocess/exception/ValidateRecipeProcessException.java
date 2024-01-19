package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ValidateRecipeProcessException extends CustomException {

    public ValidateRecipeProcessException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
