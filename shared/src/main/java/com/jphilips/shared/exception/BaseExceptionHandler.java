package com.jphilips.shared.exception;


import com.jphilips.shared.dto.ExceptionResponseDto;
import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.custom.BaseException;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.exception.util.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public abstract class BaseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex, WebRequest request) {

        List<Error> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return buildResponseFrom(new AppException(ErrorCode.FIELD_VALUE_ERROR), errors, request);

    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponseDto> handleAppExceptions(AppException ex, WebRequest request) {

        return buildResponseFrom(ex, request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleOtherExceptions(Exception ex, WebRequest request) {

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return buildResponseFrom(new AppException(ErrorCode.INTERNAL_SERVER_ERROR), request);

    }

    // ----Helper-Methods----

    public ResponseEntity<ExceptionResponseDto> buildResponseFrom(BaseException ex, List<Error> errors, WebRequest request) {

        var httpStatus = ex.getErrorCode().getStatus();
        var code = ex.getErrorCode().getCode();

        var exceptionResponseDto = ExceptionResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .code(code)
                .value(ex.getMessage() == null || ex.getMessage().isBlank() ? null : ex.getMessage())
                .errors(errors == null || errors.isEmpty() ? null : errors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(exceptionResponseDto);
    }

    public ResponseEntity<ExceptionResponseDto> buildResponseFrom(BaseException ex, WebRequest request) {
        return buildResponseFrom(ex, null, request);
    }

}
