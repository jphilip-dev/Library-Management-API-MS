package com.jphilips.library.bookinventory.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BookInventoryNotFoundException extends AppException {
    public BookInventoryNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BookInventoryNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
