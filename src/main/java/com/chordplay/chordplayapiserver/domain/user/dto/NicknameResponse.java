package com.chordplay.chordplayapiserver.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameResponse {
    private String nickname;

    public NicknameResponse(String nickname) {
        this.nickname = nickname;
    }
}
