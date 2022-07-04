package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;

@Document(collection = "WATCH_HISTORY")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class WatchHistory {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy= true)
    private Video video;
    private Timestamp last_played;
    private Long play_count;
}
