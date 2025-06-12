package com.jphilips.library.auth.exception;

import com.jphilips.shared.dto.ExceptionResponseDto;
import com.jphilips.shared.exception.BaseExceptionHandler;
import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponseDto> handleJwtExceptions(JwtException ex, WebRequest request){
        return buildResponseFrom(
                new AppException(ex instanceof ExpiredJwtException ? ErrorCode.AUTH_ERROR_JWT_EXPIRED : ErrorCode.AUTH_ERROR_JWT_INVALID),
                request
        );
    }
}
