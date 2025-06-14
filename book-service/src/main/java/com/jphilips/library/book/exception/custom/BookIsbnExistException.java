package com.jphilips.library.book.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BookIsbnExistException extends AppException {
    public BookIsbnExistException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BookIsbnExistException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
