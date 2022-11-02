package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInformationResponse {

    private String id;
    private String nickname;
    private String username;
    private String email;

    public UserInformationResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
