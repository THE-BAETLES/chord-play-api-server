package com.chordplay.chordplayapiserver.domain.video.dto;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.api.services.youtube.model.SearchResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VideoResponse {

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


    public VideoResponse(String id, String thumbnailPath, String title, String genre, String singer, List<String> tags, int length, LocalDateTime createdAt, int difficultyAvg, int playCount) {
        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.tags = tags;
        this.length = length;
        this.createdAt = createdAt.toString();
        this.difficultyAvg = difficultyAvg;
        this.playCount = playCount;
        this.sheetCount = (sheetCount == null) ? 0 : sheetCount;
    }

    public VideoResponse(Video video){
        this.id = video.getId();
        this.thumbnailPath = video.getThumbnailPath();
        this.title = video.getTitle();
        this.genre = video.getGenre();
        this.singer = video.getSinger();
        this.tags = video.getTags();
        this.length = video.getLength();
        this.createdAt = video.getCreatedAt().toString();
        this.difficultyAvg = video.getDifficultyAvg();
        this.playCount = video.getPlayCount();
        this.sheetCount = (sheetCount == null) ? 0 : sheetCount;
    }

    public VideoResponse(Video video, Long sheetCount){
        this.id = video.getId();
        this.thumbnailPath = video.getThumbnailPath();
        this.title = video.getTitle();
        this.genre = video.getGenre();
        this.singer = video.getSinger();
        this.tags = video.getTags();
        this.length = video.getLength();
        this.createdAt = video.getCreatedAt().toString();
        this.difficultyAvg = video.getDifficultyAvg();
        this.playCount = video.getPlayCount();
        this.sheetCount = (sheetCount == null) ? 0 : sheetCount;
    }

    public VideoResponse(com.google.api.services.youtube.model.Video youtubeVideo){
        this.id = youtubeVideo.getId();
        this.thumbnailPath = youtubeVideo.getSnippet().getThumbnails().getHigh().getUrl();
        this.title = youtubeVideo.getSnippet().getTitle();
        this.singer = youtubeVideo.getSnippet().getChannelTitle();
        this.tags = youtubeVideo.getSnippet().getTags() == null ? new ArrayList<String>(): youtubeVideo.getSnippet().getTags();
        String videoDuration = youtubeVideo.getContentDetails().getDuration();
        this.length = convertToMs(videoDuration);
        String rfc3339String =youtubeVideo.getSnippet().getPublishedAt().toStringRfc3339();
        this.createdAt = OffsetDateTime.parse(rfc3339String).toString();
        this.difficultyAvg = 0;
        this.playCount = 0;
        this.sheetCount = 0L;
    }

    public VideoResponse(SearchResult youtubeSearchResult){
        this.id = youtubeSearchResult.getId().getVideoId();
        this.thumbnailPath = youtubeSearchResult.getSnippet().getThumbnails().getHigh().getUrl();
        this.title = youtubeSearchResult.getSnippet().getTitle();
        this.singer = youtubeSearchResult.getSnippet().getChannelTitle();
        String rfc3339String = youtubeSearchResult.getSnippet().getPublishedAt().toString();
        this.createdAt = OffsetDateTime.parse(rfc3339String).toString();
        this.createdAt = this.createdAt.substring(0,this.createdAt.length()-1);
        this.tags = new ArrayList<String>();
        this.difficultyAvg = 0;
        this.playCount = 0;
        this.sheetCount = 0L;
        this.length = 0;
        this.genre = "";
    }


    private int convertToMs(String youtubeDuration){
        Duration dur = Duration.parse(youtubeDuration);
        return (dur.toSecondsPart() + dur.toMinutesPart()*60 + dur.toHoursPart()*3600) *1000;
    }
}
