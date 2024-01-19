package org.springeel.oneclickrecipe.domain.order.exception;

import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;

public class AlreadyProcessesOrderException extends CustomException {

    public AlreadyProcessesOrderException(
        final ErrorCode errorCode) {
        super(errorCode);
    }
}
