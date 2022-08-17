package com.chordplay.chordplayapiserver.global.exception;

import lombok.Getter;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Common
    SHEET_CREATION_FAIL(500, "S001", "Sheet creation failed : ai server failed"),
    SHEET_DATA_NOT_FOUND(400, "S002", "Sheet data not found"),
    SHEET_NOT_FOUND(400, "S003", "Sheet not found"),
    YOUTUBE_SERVER_ERROR(500, "V001", "GoogleJsonResponseException: youtube server error"),
    VIDEO_NOT_FOUND(400, "V001", "Video not found"),
    IO_EXCEPTION_ERROR(500, "C001", "IoException: server error"),
    UNAUTHORIZED(401, "C401", "Unauthorized"),
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