package com.chordplay.chordplayapiserver.global.dto;

import com.chordplay.chordplayapiserver.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T response, int status) {
        return new ApiResponse<>(Integer.toString(status),"success", response);
    }
    public static ApiResponse<?> error(ErrorCode e, int status) {
        return new ApiResponse<>(Integer.toString(status), "fail", e);
    }
}
