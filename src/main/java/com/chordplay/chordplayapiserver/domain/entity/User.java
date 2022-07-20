package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Document(collection = "USER")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
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
    private String social_type;
    private String country;
    private String language;
    private String gender;
    private String level;
    private Long experience;
    private String performer_grade;
    private String membership;

    @DocumentReference(lazy = true)
    private List<Video> signupFavorite;

    private List<MyVideo> myCollection;

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            List<String> roleList  = Arrays.asList(this.roles.split(","));
            return roleList;
        }
        return null;
    }

    @Builder
    public User(String id, String nickname, String username, String firebaseUid, String roles, String password, String email, String social_type, String country, String language, String gender, String level, Long experience, String performer_grade, String membership, List<Video> signupFavorite, List<MyVideo> myCollection) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.firebaseUid = firebaseUid;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.social_type = social_type;
        this.country = country;
        this.language = language;
        this.gender = gender;
        this.level = level;
        this.experience = experience;
        this.performer_grade = performer_grade;
        this.membership = membership;
        this.signupFavorite = signupFavorite;
        this.myCollection = myCollection;
    }

    public User(String id){
        this.id = id;
    }

}