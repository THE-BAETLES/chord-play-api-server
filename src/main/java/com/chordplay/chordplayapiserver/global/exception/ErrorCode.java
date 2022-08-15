package com.chordplay.chordplayapiserver.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    SHEET_CREATION_FAIL(500, "S001", "Sheet creation failed : ai server failed"),
    SHEET_DATA_NOT_FOUND(400, "S002", "Sheet data not found"),
    YOUTUBE_SERVER_ERROR(500, "V001", "GoogleJsonResponseException: youtube server error"),
    IO_EXCEPTION_ERROR(500, "C001", "IoException: server error"),
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