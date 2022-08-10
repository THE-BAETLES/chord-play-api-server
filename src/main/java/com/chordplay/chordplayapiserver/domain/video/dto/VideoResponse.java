package com.chordplay.chordplayapiserver.domain.video.dto;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.api.services.youtube.model.SearchResult;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VideoResponse {

    private String id;
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    private List<String> tags;
    private String length;
    private String createdAt;
    private int difficultyAvg;
    private int playCount;
    private Long sheetCount;


    public VideoResponse(String id, String thumbnailPath, String title, String genre, String singer, List<String> tags, String length, String createdAt, int difficultyAvg, int playCount) {
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
    }

    public VideoResponse(Video video){
        this.id = video.getId();
        this.thumbnailPath = video.getThumbnailPath();
        this.title = video.getTitle();
        this.genre = video.getGenre();
        this.singer = video.getSinger();
        this.tags = video.getTags();
        this.length = video.getLength();
        this.createdAt = video.getCreatedAt();
        this.difficultyAvg = video.getDifficultyAvg();
        this.playCount = video.getPlayCount();
    }

    public VideoResponse(Video video, Long sheetCount){
        this.id = video.getId();
        this.thumbnailPath = video.getThumbnailPath();
        this.title = video.getTitle();
        this.genre = video.getGenre();
        this.singer = video.getSinger();
        this.tags = video.getTags();
        this.length = video.getLength();
        this.createdAt = video.getCreatedAt();
        this.difficultyAvg = video.getDifficultyAvg();
        this.playCount = video.getPlayCount();
        this.sheetCount = (sheetCount == null) ? 0 : sheetCount;
    }

    public VideoResponse(com.google.api.services.youtube.model.Video youtubeVideo){
        this.id = youtubeVideo.getId();
        this.thumbnailPath = youtubeVideo.getSnippet().getThumbnails().getHigh().getUrl();
        this.title = youtubeVideo.getSnippet().getTitle();
        this.singer = youtubeVideo.getSnippet().getChannelTitle();
        this.tags = youtubeVideo.getSnippet().getTags();
        String videoDuration = youtubeVideo.getContentDetails().getDuration();
        this.length = makeLengthFormat(videoDuration);
        this.createdAt = youtubeVideo.getSnippet().getPublishedAt().toString(); // DateTime 변환 필요
        this.difficultyAvg = 0;
        this.playCount = 0;
        this.sheetCount = 0L;
    }

    public VideoResponse(SearchResult youtubeSearchResult){
        this.id = youtubeSearchResult.getId().getVideoId();
        this.thumbnailPath = youtubeSearchResult.getSnippet().getThumbnails().getHigh().getUrl();
        this.title = youtubeSearchResult.getSnippet().getTitle();
        this.singer = youtubeSearchResult.getSnippet().getChannelTitle();
        this.createdAt = youtubeSearchResult.getSnippet().getPublishedAt().toString(); // DateTime 변환 필요

        this.tags = new ArrayList<String>();
        this.difficultyAvg = 0;
        this.playCount = 0;
        this.sheetCount = 0L;
        this.length = "";
        this.genre = "";
    }


    private String makeLengthFormat(String youtubeDuration) {
        String length;
        Duration dur = Duration.parse(youtubeDuration);
        if (dur.toHours() > 0) {
            length = String.format("%d:%02d:%02d",
                    dur.toHours(),
                    dur.toMinutesPart(),
                    dur.toSecondsPart());
        } else {
            length = String.format("%02d:%02d",
                    dur.toMinutesPart(),
                    dur.toSecondsPart());
        }
        return length;
    }
}
