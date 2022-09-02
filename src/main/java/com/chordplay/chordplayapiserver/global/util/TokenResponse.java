package com.chordplay.chordplayapiserver.global.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponse {
    String kind;
    String idToken;
    String refreshToken;
    String expiresIn;
    String isNewUser;
}
