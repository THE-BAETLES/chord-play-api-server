package com.chordplay.chordplayapiserver.domain.video.exception;

import com.chordplay.chordplayapiserver.global.exception.ErrorCode;
import com.chordplay.chordplayapiserver.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class VideoExceptionHandler {

    @ExceptionHandler(value = {YoutubeServerException.class})
    public ResponseEntity<ErrorResponse> GoogleJsonResponseException(YoutubeServerException e){
        log.error("Youtube Server Exception");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.YOUTUBE_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {VideoNotFoundException.class})
    public ResponseEntity<ErrorResponse> VideoNotFoundException(VideoNotFoundException e){
        log.error("Video Not Found Exception");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.VIDEO_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {IncorrectGradeInputException.class})
    public ResponseEntity<ErrorResponse> IncorrectGradeInputException(IncorrectGradeInputException e){
        log.error("Incorrect Grade Input Exception");
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INCORRECT_GRADE_INPUT_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
