package com.jphilips.library.borrowing.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BookRecordNotFoundException extends AppException {
    public BookRecordNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BookRecordNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
