package com.chordplay.chordplayapiserver.global.exception;

import com.google.rpc.ErrorInfoOrBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;


    public ErrorResponse(String message, int status, List<FieldError> errors, String code) {
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.code = code;
    }


    public static ErrorResponse of (ErrorCode errorCode, List<FieldError> errors){
        return new ErrorResponse(errorCode.getMessage(),errorCode.getStatus(),errors,errorCode.getCode());
    }
    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return ErrorResponse.of(code, FieldError.of(bindingResult));
    }
    public static ErrorResponse of (ErrorCode errorCode){
        return new ErrorResponse(errorCode.getMessage(),errorCode.getStatus(),new ArrayList<FieldError>(),errorCode.getCode());
    }



    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
