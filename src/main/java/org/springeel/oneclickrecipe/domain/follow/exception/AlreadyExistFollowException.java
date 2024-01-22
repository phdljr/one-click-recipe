package org.springeel.oneclickrecipe.domain.follow.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistFollowException extends CustomException {

    public AlreadyExistFollowException(ErrorCode errorCode) {
        super(errorCode);
    }
}

