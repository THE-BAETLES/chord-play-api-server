package com.chordplay.chordplayapiserver.domain.user.exception;

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
public class UserExceptionHandler {

    @ExceptionHandler(value = {NicknameDuplicationException.class})
    public ResponseEntity<Object> handleNicknameDuplicationException(NicknameDuplicationException e){
        log.error("Nickname Duplication Exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NICKNAME_DUPLICATION);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotFullyJoinedException.class})
    public ResponseEntity<Object> handleNotFullyJoinedException(NotFullyJoinedException e){
        log.error("Nickname Duplication Exception",e);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_FULLY_JOINED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
