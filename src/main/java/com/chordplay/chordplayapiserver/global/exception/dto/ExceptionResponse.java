package com.chordplay.chordplayapiserver.global.exception.dto;

public class ExceptionResponse {

    private String message;

    public ExceptionResponse(Exception exception){
        this.message = exception.getMessage();  // 후에 getLocalizedMessage() check
    }
}
