package com.jphilips.shared.exception.custom;

import com.jphilips.shared.exception.errorcode.ErrorCode;

public class AppException extends BaseException{
    public AppException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AppException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
