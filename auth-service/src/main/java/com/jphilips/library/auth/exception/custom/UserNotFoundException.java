package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
