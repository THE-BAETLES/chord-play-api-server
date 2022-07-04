package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;
@Document(collection = "DIFFICULTY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Difficulty {
    @Id
    private String id;

    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy = true)
    private Video video;
    private Long difficulty;
    private Timestamp time;

    @Builder
    public Difficulty(String id, User user, Video video, Long difficulty, Timestamp time) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.difficulty = difficulty;
        this.time = time;
    }
}
