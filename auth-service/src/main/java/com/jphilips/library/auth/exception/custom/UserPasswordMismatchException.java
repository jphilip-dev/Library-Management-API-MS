package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class UserPasswordMismatchException extends AppException {
    public UserPasswordMismatchException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserPasswordMismatchException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
