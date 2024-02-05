package org.springeel.oneclickrecipe.domain.user.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyExistsEmailException extends CustomException {

    public AlreadyExistsEmailException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
