package com.chordplay.chordplayapiserver.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
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
