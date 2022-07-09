package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "USER")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class User {


    @Id
    private String id;
    private String nickname;
    private String social_id;
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

    @Builder
    public User(String id, String nickname, String social_id, String social_type, String country, String language, String gender, String level, Long experience, String performer_grade, String membership, List<Video> signupFavorite, List<MyVideo> myCollection) {
        this.id = id;
        this.nickname = nickname;
        this.social_id = social_id;
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