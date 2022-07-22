package com.chordplay.chordplayapiserver.domain.user.exception;

import com.chordplay.chordplayapiserver.global.exception.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {NicknameDuplicationException.class})
    public ResponseEntity<Object> handleNicknameDuplicationException(NicknameDuplicationException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotFullyJoinedException.class})
    public ResponseEntity<Object> handleNotFullyJoinedException(NotFullyJoinedException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
