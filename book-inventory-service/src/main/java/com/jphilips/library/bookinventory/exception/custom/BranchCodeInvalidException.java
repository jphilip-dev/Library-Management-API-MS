package com.jphilips.library.bookinventory.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BranchCodeInvalidException extends AppException {
    public BranchCodeInvalidException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BranchCodeInvalidException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
