package com.chordplay.chordplayapiserver.domain.video.exception;

import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetCreationFailException;
import com.chordplay.chordplayapiserver.global.exception.ErrorCode;
import com.chordplay.chordplayapiserver.global.exception.ErrorResponse;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class VideoExceptionHandler {

    @ExceptionHandler(value = {YoutubeServerException.class})
    public ResponseEntity<ErrorResponse> GoogleJsonResponseException(YoutubeServerException e){
        log.error("Youtube Server Exception");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.YOUTUBE_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
