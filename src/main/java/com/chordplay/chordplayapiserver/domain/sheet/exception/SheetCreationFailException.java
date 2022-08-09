package com.chordplay.chordplayapiserver.domain.sheet.exception;

import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import com.chordplay.chordplayapiserver.global.exception.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class SheetCreationFailException extends RuntimeException{
}
