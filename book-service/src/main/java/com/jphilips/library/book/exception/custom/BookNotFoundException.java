package com.jphilips.library.book.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BookNotFoundException extends AppException {
    public BookNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BookNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
