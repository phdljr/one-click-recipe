package org.springeel.oneclickrecipe.domain.recipelike.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsRecipeLikeException extends CustomException {

    public AlreadyExistsRecipeLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
