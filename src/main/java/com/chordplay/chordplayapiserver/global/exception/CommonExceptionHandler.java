package com.chordplay.chordplayapiserver.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(value = {RuntimeIoException.class})
    public ResponseEntity<ErrorResponse> RuntimeIoException(RuntimeIoException e){
        log.error("IOException: " + e.getCause() + " : " + e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.IO_EXCEPTION_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<ErrorResponse> UnauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<ErrorResponse> ForbiddenException(ForbiddenException e){
        log.error("ForbiddenException");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.FORBIDDEN);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> validException(
            MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException ",e);
        String message = String.format("파라미터 유효성 검증 실패 : %s",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT, e.getBindingResult());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
