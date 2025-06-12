package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class ExistingEmailException extends AppException {
    public ExistingEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ExistingEmailException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
