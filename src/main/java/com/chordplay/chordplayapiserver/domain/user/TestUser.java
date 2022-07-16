package com.chordplay.chordplayapiserver.domain.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Document(collection = "TEST_USER")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class TestUser {

    public void setRoles(String role) {
        this.roles = roles;
    }

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String roles;    //USER, ADMIN

    private String provider;
    private String providerId;

    public TestUser(String id, String username, String password, String email, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
    @Builder
    public TestUser(String id, String username, String password, String email, String roles, String provider, String providerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.provider = provider;
        this.providerId = providerId;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            List<String> roleList  = Arrays.asList(this.roles.split(","));
            return roleList;
        }
        return null;
    }
}