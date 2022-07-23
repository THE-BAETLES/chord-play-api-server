package com.chordplay.chordplayapiserver.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckDuplicationRequest {
    private String nickname;

    public CheckDuplicationRequest(String nickname) {
        this.nickname = nickname;
    }


}
