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
import java.time.LocalDateTime;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetResponse {
    private String id;
    private String videoId;
    private String userId;
    private String title;
    private String createdAt;
    private String updatedAt;

    private Long likeCount;

    public SheetResponse(Sheet sheet) {
        this.id = sheet.getId();
        this.videoId = sheet.getVideo().getId();
        this.userId = sheet.getUser().getId();
        this.title = sheet.getTitle();
        this.createdAt = sheet.getCreatedAt().toString();
        this.updatedAt = sheet.getUpdatedAt().toString();
        this.likeCount = sheet.getLikeCount();
    }
}