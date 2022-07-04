package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "USER")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
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
}