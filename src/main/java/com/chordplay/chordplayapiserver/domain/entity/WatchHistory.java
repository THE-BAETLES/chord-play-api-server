package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;

@Document(collection = "WATCH_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WatchHistory {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy= true)
    private Video video;
    private Timestamp last_played;
    private Long play_count;

    @Builder
    public WatchHistory(String id, User user, Video video, Timestamp last_played, Long play_count) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.last_played = last_played;
        this.play_count = play_count;
    }
}
