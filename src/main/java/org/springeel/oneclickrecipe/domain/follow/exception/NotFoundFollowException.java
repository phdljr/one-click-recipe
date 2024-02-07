package org.springeel.oneclickrecipe.domain.follow.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFoundFollowException extends CustomException {

    public NotFoundFollowException(ErrorCode errorCode) {
        super(errorCode);
    }
}
