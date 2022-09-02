package com.chordplay.chordplayapiserver.global.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequest {
    private String token;
    private Boolean returnSecureToken;

    @Builder
    public TokenRequest(String token, Boolean returnSecureToken) {
        this.token = token;
        this.returnSecureToken = returnSecureToken;
    }
}
