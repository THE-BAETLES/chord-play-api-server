package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetAiRequest {
    private String videoId;
    private int status;

    @Builder
    public SheetAiRequest(String videoId, int status) {
        this.videoId = videoId;
        this.status = status;
    }

    public SheetAiRequest() {
    }

    public Sheet toEntity(User user){
        return Sheet.builder().user(user)
                .video(new Video(videoId))
                .build(); //title create_at update_at like_count
    }

}
