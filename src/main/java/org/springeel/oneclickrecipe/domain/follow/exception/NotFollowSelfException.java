package org.springeel.oneclickrecipe.domain.follow.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class NotFollowSelfException extends CustomException {

    public NotFollowSelfException(ErrorCode errorCode) {
        super(errorCode);
    }
}
