package com.jphilips.shared.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // --- Common Errors ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR_INTERNAL_SERVER"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "ERROR_BAD_REQUEST"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ERROR_UNAUTHORIZED"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "ERROR_FORBIDDEN"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "ERROR_NOT_FOUND"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "ERROR_METHOD_NOT_ALLOWED"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "ERROR_VALIDATION"),
    CONFLICT(HttpStatus.CONFLICT, "ERROR_CONFLICT"),

    FIELD_VALUE_ERROR(HttpStatus.BAD_REQUEST, "ERROR_FIELD_VALUE"),

    // Custom Error codes (Service Specific)
    AUTH_ERROR_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERROR_USER_NOT_FOUND"),
    AUTH_ERROR_OWNERSHIP_MISMATCH(HttpStatus.FORBIDDEN, "ERROR_OWNERSHIP_MISMATCH");


    private final HttpStatus status;
    private final String code;

}
