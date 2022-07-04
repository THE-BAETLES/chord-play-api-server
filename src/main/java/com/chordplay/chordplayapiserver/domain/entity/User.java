package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    private String id;
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
    private UserNickname nicknameDoc;

    @Builder
    public User(String id, String social_id, String social_type, String country, String language, String gender, String level, Long experience, String performer_grade, String membership, UserNickname nicknameDoc) {
        this.id = id;
        this.social_id = social_id;
        this.social_type = social_type;
        this.country = country;
        this.language = language;
        this.gender = gender;
        this.level = level;
        this.experience = experience;
        this.performer_grade = performer_grade;
        this.membership = membership;
        this.nicknameDoc = nicknameDoc;
    }
}