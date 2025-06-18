package com.jphilips.library.bookinventory.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BookClientException extends AppException {
    public BookClientException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BookClientException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
