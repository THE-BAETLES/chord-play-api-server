package com.chordplay.chordplayapiserver.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collection = "WATCH_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WatchHistory {
    @Id
    private String id;
    @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    private Video video;
    @Field(value = "last_played")
    private LocalDateTime lastPlayed;
    @Field(value = "play_count")
    private Long playCount;

    @Builder
    public WatchHistory(String id, User user, Video video, LocalDateTime lastPlayed, Long playCount) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.lastPlayed = lastPlayed;
        this.playCount = playCount;
    }
}
