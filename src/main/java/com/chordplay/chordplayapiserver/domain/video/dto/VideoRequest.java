package com.chordplay.chordplayapiserver.domain.video.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VideoRequest {
    @JsonProperty("_id")
    private String id;
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    private List<String> tags;
    private int length;
    private String createdAt;
    private int difficultyAvg;
    private int playCount;
    private Long sheetCount;

    public VideoRequest(String id, String thumbnailPath, String title, String genre, String singer, List<String> tags, int length, String createdAt, int difficultyAvg, int playCount, Long sheetCount) {
        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.tags = tags;
        this.length = length;
        this.createdAt = createdAt;
        this.difficultyAvg = difficultyAvg;
        this.playCount = playCount;
        this.sheetCount = sheetCount;
    }
}
