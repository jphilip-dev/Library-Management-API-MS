package com.jphilips.library.auth.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class MissingJwtException extends AppException {

    public MissingJwtException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MissingJwtException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
