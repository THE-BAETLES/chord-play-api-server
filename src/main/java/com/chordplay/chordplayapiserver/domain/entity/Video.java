package com.chordplay.chordplayapiserver.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.Aggregation;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "VIDEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Video {

    @Id
    private String id;
    @Builder
    public Video(String id, String thumbnailPath, String title, String genre, String singer, List<String> tags) {
        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.tags = tags;
        this.difficultyAvg = 0;
        this.playCount = 0;
    }

    @Field("thumbnail_path")
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    private List<String> tags;
    private String length;
    private String createdAt;
    @Field("difficulty_avg")
    private int difficultyAvg;
    @Field("play_count")
    private int playCount;
    public Video(String id){
        this.id = id;
    }

    public Video(com.google.api.services.youtube.model.Video youtubeVideo){
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

    public static List<Video> GetDummyVideos () {
        List<Video> videos = new ArrayList<Video>();
        videos.add(
                Video.builder()
                        .id("f6YDKF0LVWw")
                        .thumbnailPath("https://img.youtube.com/vi/f6YDKF0LVWw/0.jpg")
                        .title("NAYEON \"POP!\" M/V")
                        .genre("idol")
                        .singer("JYP Entertainment")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("--FmExEAsM8")
                        .thumbnailPath("https://img.youtube.com/vi/--FmExEAsM8/0.jpg")
                        .title("IVE 아이브 'ELEVEN' MV")
                        .genre("idol")
                        .singer("starshipTV")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("aZCfbL5oIeI")
                        .thumbnailPath("https://img.youtube.com/vi/aZCfbL5oIeI/0.jpg'")
                        .title("Eul (Feat. BIG Naughty) (을 (Feat. BIG Naughty (서동현)))")
                        .genre("hip-hop")
                        .singer("GIRIBOY")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("pnaQ9CbE6P0")
                        .thumbnailPath("https://img.youtube.com/vi/pnaQ9CbE6P0/0.jpg")
                        .title("자우림 '스물다섯, 스물하나' 어쿠스틱커버 by 장범준 Acoustic COVER")
                        .genre("performance")
                        .singer("장범준")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("YwC0m0XaD2E")
                        .thumbnailPath("https://img.youtube.com/vi/YwC0m0XaD2E/0.jpg")
                        .title("최고의 피카츄 월드컵 (※동심파괴 주의)")
                        .genre("idol")
                        .singer("침착맨")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("aZCfbL5oIeI")
                        .thumbnailPath("https://img.youtube.com/vi/aZCfbL5oIeI/0.jpg")
                        .title("Eul (Feat. BIG Naughty) (을 (Feat. BIG Naughty (서동현)))")
                        .genre("hip-hop")
                        .singer("GIRIBOY")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("pnaQ9CbE6P0")
                        .thumbnailPath("https://img.youtube.com/vi/pnaQ9CbE6P0/0.jpg")
                        .title("자우림 '스물다섯, 스물하나' 어쿠스틱커버 by 장범준 Acoustic COVER")
                        .genre("performance")
                        .singer("장범준")
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("YwC0m0XaD2E")
                        .thumbnailPath("https://img.youtube.com/vi/YwC0m0XaD2E/0.jpg")
                        .title("최고의 피카츄 월드컵 (※동심파괴 주의)")
                        .genre("idol")
                        .singer("침착맨")
                        .build()
        );
        return videos;
    }
}
