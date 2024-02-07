package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class ForbiddenAccessNicknameException extends CustomException {
    public ForbiddenAccessNicknameException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
