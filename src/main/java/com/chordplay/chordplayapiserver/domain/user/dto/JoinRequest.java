package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.google.firebase.auth.FirebaseToken;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class JoinRequest {
    private String firebaseUid;
    private String username;
    private String email;
    @Builder
    public JoinRequest(String firebaseUid, String username, String email) {
        this.firebaseUid = firebaseUid;
        this.username = username;
        this.email = email;
    }

    public JoinRequest(FirebaseToken token) {
        this.username = token.getName();
        this.email = token.getEmail();
        this.firebaseUid = token.getUid();
    }


    public User toEntity(String roles){
        return User.builder().username(username).email(email).firebaseUid(firebaseUid).roles(roles).build();
    }
}
