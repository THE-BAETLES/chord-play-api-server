package com.chordplay.chordplayapiserver.domain.video.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@ToString
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WatchHistoryResponse {

    private VideoResponse video;
    private String lastPlayed;
    private Long playCount;


    public WatchHistoryResponse(VideoResponse video, String lastPlayed, Long playCount) {
        this.video = video;
        this.lastPlayed = lastPlayed;
        this.playCount = playCount;
    }
    public WatchHistoryResponse(WatchHistory watchHistory){
        this.video = new VideoResponse(watchHistory.getVideo());
        this.lastPlayed = watchHistory.getLastPlayed().toString();
        this.playCount = this.getPlayCount();
    }
}
