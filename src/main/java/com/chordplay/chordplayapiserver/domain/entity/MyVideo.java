package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MyVideo {

    @DocumentReference(lazy = true)
    private Video video;
    private Timestamp last_played;

    @Builder
    public MyVideo(Video video, Timestamp last_played) {
        this.video = video;
        this.last_played = last_played;
    }
}
