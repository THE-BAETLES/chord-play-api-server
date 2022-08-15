package com.chordplay.chordplayapiserver.domain.entity;

import com.chordplay.chordplayapiserver.domain.entity.item.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Document(collection = "USER")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    @Id
    private String id;
    private String nickname;

    private String username;

    @Field(value = "firebase_uid")
    private String firebaseUid;

    private String roles;

    private String password;

    private String email;
    private Country country;
    private Language language;
    private Gender gender;

    @Field(value = "performer_grade")
    private PerformerGrade performerGrade;
    private Membership membership;

    @DBRef(lazy = true)
    private List<Video> earlyFavoriteSongs;

    private List<MyVideo> myCollection;

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            List<String> roleList  = Arrays.asList(this.roles.split(","));
            return roleList;
        }
        return null;
    }

    @Builder
    public User(String id, String nickname, String username, String firebaseUid, String roles, String password, String email, Country country, Language language, Gender gender, PerformerGrade performerGrade, Membership membership, List<Video> earlyFavoriteSongs, List<MyVideo> myCollection) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.firebaseUid = firebaseUid;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.country = country;
        this.language = language;
        this.gender = gender;
        this.performerGrade = performerGrade;
        this.membership = membership;
        this.earlyFavoriteSongs = earlyFavoriteSongs;
        this.myCollection = myCollection;
    }



    public User(String id){
        this.id = id;
    }

}