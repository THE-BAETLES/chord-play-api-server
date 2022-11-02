package com.chordplay.chordplayapiserver.domain.sheetLike.exception;

import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetCreationFailException;
import com.chordplay.chordplayapiserver.global.exception.ErrorCode;
import com.chordplay.chordplayapiserver.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SheetLikeExceptionHandler {
    @ExceptionHandler(value = {SheetLikeNotFoundException.class})
    public ResponseEntity<ErrorResponse> SheetCreationFailException(SheetLikeNotFoundException e){
        log.error("Sheet-like not found exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.SHEET_LIKE_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
