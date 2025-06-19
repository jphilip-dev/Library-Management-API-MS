package com.jphilips.library.bookinventory.exception.custom;

import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;

public class BranchNotFoundException extends AppException {

    public BranchNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BranchNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
