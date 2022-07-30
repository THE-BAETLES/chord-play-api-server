package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MyVideo {

    @DocumentReference(lazy = true)
    private Video video;
    @Field("last_played")
    private Timestamp lastPlayed;

    @Builder
    public MyVideo(Video video, Timestamp lastPlayed) {
        this.video = video;
        this.lastPlayed = lastPlayed;
    }
}
