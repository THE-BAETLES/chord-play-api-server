package com.chordplay.chordplayapiserver.global.exception.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(Exception exception){
        this.message = exception.getMessage();  // 후에 getLocalizedMessage() check
    }
}
