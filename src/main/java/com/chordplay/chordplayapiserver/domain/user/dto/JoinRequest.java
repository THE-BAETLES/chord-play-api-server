package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.user.TestUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class JoinRequest {

    private String firebase_uid;
    private String username;
    private String password;
    private String email;

    @Builder
    public JoinRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public TestUser toEntity(String roles){
        return TestUser.builder().username(username).email(email).password(password).roles(roles).build();
    }
}
