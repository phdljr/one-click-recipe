package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class DuplicateNicknameException extends CustomException {

    public DuplicateNicknameException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
