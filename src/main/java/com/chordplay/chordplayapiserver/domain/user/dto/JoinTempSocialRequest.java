package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.google.firebase.auth.FirebaseToken;
import lombok.Builder;

public class JoinTempSocialRequest {
    private String firebaseUid;
    private String username;
    private String email;

    @Builder
    public JoinTempSocialRequest(FirebaseToken token) {
        this.username = token.getName();
        this.email = token.getEmail();
        this.firebaseUid = token.getUid();
    }

    public User toEntity(){
        return User.builder().username(username).email(email).firebaseUid(firebaseUid).build();
    }
}
