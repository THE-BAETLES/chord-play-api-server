package com.chordplay.chordplayapiserver.domain.sheet.exception;


import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import com.chordplay.chordplayapiserver.domain.user.exception.NotFullyJoinedException;
import com.chordplay.chordplayapiserver.global.exception.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SheetExceptionHandler {




//    @ExceptionHandler(value = {NotFullyJoinedException.class})
//    public ResponseEntity<Object> handleNotFullyJoinedException(NotFullyJoinedException ex){
//        ExceptionResponse exceptionResponse = new ExceptionResponse(ex);
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
//    }
}
