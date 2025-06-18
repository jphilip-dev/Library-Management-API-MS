package com.jphilips.library.bookinventory.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class NotEnoughStockException extends AppException {
    public NotEnoughStockException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotEnoughStockException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
