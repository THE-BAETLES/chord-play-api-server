package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "MY_COLLECTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MyCollection {
    @Id
    private String id;
    private Video video;
    private Timestamp last_played;

    @Builder
    public MyCollection(String id, Video video, Timestamp last_played) {
        this.id = id;
        this.video = video;
        this.last_played = last_played;
    }
}
