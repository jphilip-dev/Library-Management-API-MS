package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class UserInactiveException extends AppException {
    public UserInactiveException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserInactiveException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
