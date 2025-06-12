package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class OwnershipException extends AppException {
    public OwnershipException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OwnershipException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
