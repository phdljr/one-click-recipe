package org.springeel.oneclickrecipe.domain.recipeprocess.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsRecipeProcessException extends CustomException {

    public AlreadyExistsRecipeProcessException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
