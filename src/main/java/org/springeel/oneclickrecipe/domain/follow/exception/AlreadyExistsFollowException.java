package org.springeel.oneclickrecipe.domain.follow.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsFollowException extends CustomException {

    public AlreadyExistsFollowException(ErrorCode errorCode) {
        super(errorCode);
    }
}

