package org.springeel.oneclickrecipe.domain.recipe.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsRecipeException extends CustomException {

    public AlreadyExistsRecipeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
