package com.chordplay.chordplayapiserver.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
