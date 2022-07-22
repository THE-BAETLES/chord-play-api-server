package com.chordplay.chordplayapiserver.global.exception.dto;

import java.util.Collection;

public class CommonExceptionResponse {

    private String message;

    public CommonExceptionResponse(Exception exception){
        this.message = exception.getMessage();  // 후에 getLocalizedMessage() check
    }
}
