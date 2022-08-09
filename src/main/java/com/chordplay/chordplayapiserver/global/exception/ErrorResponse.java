package com.chordplay.chordplayapiserver.global.exception;

import com.google.rpc.ErrorInfoOrBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

    }

    public ErrorResponse(String message, int status, List<FieldError> errors, String code) {
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.code = code;
    }

    public static ErrorResponse of (ErrorCode errorCode, List<FieldError> errors){
        return new ErrorResponse(errorCode.getMessage(),errorCode.getStatus(),errors,errorCode.getCode());
    }
    public static ErrorResponse of (ErrorCode errorCode){
        return new ErrorResponse(errorCode.getMessage(),errorCode.getStatus(),new ArrayList<FieldError>(),errorCode.getCode());
    }
}
