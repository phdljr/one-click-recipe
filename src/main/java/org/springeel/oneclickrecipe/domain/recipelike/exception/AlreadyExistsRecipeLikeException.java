package org.springeel.oneclickrecipe.domain.recipelike.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;

public class AlreadyExistsRecipeLikeException extends CustomException {
    public AlreadyExistsRecipeLikeException(RecipeLikeErrorCode recipeLikeErrorCode) {
        super(recipeLikeErrorCode);
    }
}
