package com.chordplay.chordplayapiserver.global.exception;

import lombok.Getter;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Common
    NICKNAME_DUPLICATION(409,"U001","Nickname Duplication Exception"),
    NOT_FULLY_JOINED(401,"U002", "You did not sign up for details, Please continue to signup"),
    USER_NOT_FOUND(400,"U003", "user not found exception"),
    SHEET_CREATION_FAIL(500, "S001", "Sheet creation failed : ai server failed"),
    SHEET_DATA_NOT_FOUND(400, "S002", "Sheet data not found"),
    SHEET_NOT_FOUND(400, "S003", "Sheet not found"),
    AI_SHEET_NOT_CREATED(200, "S004", "Ai sheet data has not yet been created"),
    SHEET_LIKE_NOT_FOUND(400, "S005", "Sheet like not found"),
    YOUTUBE_SERVER_ERROR(500, "V001", "GoogleJsonResponseException: youtube server error"),
    VIDEO_NOT_FOUND(400, "V002", "Video not found"),
    INCORRECT_GRADE_INPUT_EXCEPTION(400, "V003", "Incorrect grade input exception"),
    IO_EXCEPTION_ERROR(500, "C001", "IoException: server error"),
    INVALID_INPUT(400, "C002", "Invalid input"),
    UNAUTHORIZED(401, "C401", "Unauthorized"),
    FORBIDDEN(403, "C403", "Forbidden"),
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