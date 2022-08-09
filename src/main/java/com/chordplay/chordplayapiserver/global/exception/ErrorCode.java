package com.chordplay.chordplayapiserver.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    SHEET_CREATION_FAIL(500, "S001", "Sheet creation failed : ai server failed"),
    ;
    private final String code;
    private final String message;
    private int status;
    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}