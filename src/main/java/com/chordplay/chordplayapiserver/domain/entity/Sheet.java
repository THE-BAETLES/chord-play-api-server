package com.chordplay.chordplayapiserver.domain.entity;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.sql.Timestamp;

@Document(collection = "SHEET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Sheet {
    @Id
    private String id;

    @DBRef(lazy = true)
    private Video video;

    @DBRef(lazy = true)
    private User user;

    private String title;

    @Field("created_at")
    private Timestamp createdAt;
    @Field("updated_at")
    private Timestamp updatedAt;

    @Field("like_count")
    private Long likeCount;

    @Builder
    public Sheet(String id, Video video, User user, String title, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.video = video;
        this.user = user;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likeCount = 0L;
    }
}
