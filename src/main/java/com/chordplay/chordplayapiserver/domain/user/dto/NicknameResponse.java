package com.chordplay.chordplayapiserver.domain.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
