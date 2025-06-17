package com.jphilips.library.userprofile.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class UserProfileOwnershipException extends AppException {
    public UserProfileOwnershipException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserProfileOwnershipException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
