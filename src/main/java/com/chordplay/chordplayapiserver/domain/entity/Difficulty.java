package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;
@Document(collection = "DIFFICULTY")
@Getter
@RequiredArgsConstructor
public class Difficulty {
    @Id
    private String id;

    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy = true)
    private Video video;
    private Long difficulty;
    private Timestamp time;
}
