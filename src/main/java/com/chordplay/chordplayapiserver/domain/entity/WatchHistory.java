package com.chordplay.chordplayapiserver.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;

@Document(collection = "WATCH_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WatchHistory {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy= true)
    private Video video;
    private Timestamp lastPlayed;
    private Long playCount;

    @Builder
    public WatchHistory(String id, User user, Video video, Timestamp lastPlayed, Long playCount) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.lastPlayed = lastPlayed;
        this.playCount = playCount;
    }
}
