package com.chordplay.chordplayapiserver.domain.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "TEST_USER")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class TestUser {

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;

    @Builder
    public TestUser(String id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}