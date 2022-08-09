package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.firebase.auth.FirebaseToken;
import lombok.Builder;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JoinTempSocialRequest {
    private String firebaseUid;
    private String username;
    private String email;
    private String roles = "ROLE_USER";

    @Builder
    public JoinTempSocialRequest(FirebaseToken token) {
        this.username = token.getName();
        this.email = token.getEmail();
        this.firebaseUid = token.getUid();
    }

    public User toEntity(){
        return User.builder().username(username).email(email).firebaseUid(firebaseUid).roles(roles).build();
    }
}
