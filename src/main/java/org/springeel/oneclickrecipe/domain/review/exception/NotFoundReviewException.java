package org.springeel.oneclickrecipe.domain.review.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundReviewException extends CustomException {

    public NotFoundReviewException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
