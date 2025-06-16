package com.jphilips.library.userprofile.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class UserProfileNotFoundException extends AppException {
    public UserProfileNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserProfileNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
