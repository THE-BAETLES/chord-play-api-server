package com.chordplay.chordplayapiserver.domain.sheet.exception;


import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import com.chordplay.chordplayapiserver.domain.user.exception.NotFullyJoinedException;
import com.chordplay.chordplayapiserver.global.exception.ErrorCode;
import com.chordplay.chordplayapiserver.global.exception.ErrorResponse;
import com.chordplay.chordplayapiserver.global.exception.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SheetExceptionHandler {

    @ExceptionHandler(value = {SheetCreationFailException.class})
    public ResponseEntity<ErrorResponse> SheetCreationFailException(SheetCreationFailException e){
        log.error("Sheet Creation Fail Exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.SHEET_CREATION_FAIL);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {SheetDataNotFoundException.class})
    public ResponseEntity<ErrorResponse> SheetDataNotFoundException(SheetDataNotFoundException e){
        log.error("Sheet Data Not Found Exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.SHEET_DATA_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SheetNotFoundException.class})
    public ResponseEntity<ErrorResponse> SheetNotFoundException(SheetNotFoundException e){
        log.error("Sheet Not Found Exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.SHEET_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AiSheetNotCreatedException.class})
    public ResponseEntity<ErrorResponse> AiSheetNotCreatedException(AiSheetNotCreatedException e){
        log.error("Ai sheet data has not yet been created ",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.AI_SHEET_NOT_CREATED);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
