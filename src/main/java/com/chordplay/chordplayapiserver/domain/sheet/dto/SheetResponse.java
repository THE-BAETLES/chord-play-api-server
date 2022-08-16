package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.sql.Timestamp;
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetResponse {
    private String id;
    private Video video;
    private User user;
    private String title;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Long likeCount;

    public SheetResponse(Sheet sheet) {
        this.id = sheet.getId();
        this.video = sheet.getVideo();
        this.user = sheet.getUser();
        this.title = sheet.getTitle();
        this.createdAt = sheet.getCreatedAt();
        this.updatedAt = sheet.getUpdatedAt();
        this.likeCount = sheet.getLikeCount();
    }
}
