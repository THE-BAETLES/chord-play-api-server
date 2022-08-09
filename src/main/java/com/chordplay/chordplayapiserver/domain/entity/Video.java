package com.chordplay.chordplayapiserver.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "VIDEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Video {

    @Id
    private String id;
    @Builder
    public Video(String id, String thumbnailPath, String title, String genre, String singer, int difficultyAvg, int playCount) {
        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.difficultyAvg = difficultyAvg;
        this.playCount = playCount;
    }

    @Field("thumbnail_path")
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    @Field("difficulty_avg")
    private int difficultyAvg;
    @Field("play_count")
    private int playCount;

    public Video(String id){
        this.id = id;
    }

    public static List<Video> GetDummyVideos(){
        List<Video> videos = new ArrayList<Video>();
        videos.add(
                Video.builder()
                        .id("f6YDKF0LVWw")
                        .thumbnailPath("https://img.youtube.com/vi/f6YDKF0LVWw/0.jpg")
                        .title("NAYEON \"POP!\" M/V")
                        .genre("idol")
                        .singer("JYP Entertainment")
                        .difficultyAvg(2)
                        .playCount(22231)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("--FmExEAsM8")
                        .thumbnailPath("https://img.youtube.com/vi/--FmExEAsM8/0.jpg")
                        .title("IVE 아이브 'ELEVEN' MV")
                        .genre("idol")
                        .singer("starshipTV")
                        .difficultyAvg(3)
                        .playCount(123213)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("aZCfbL5oIeI")
                        .thumbnailPath("https://img.youtube.com/vi/aZCfbL5oIeI/0.jpg'")
                        .title("Eul (Feat. BIG Naughty) (을 (Feat. BIG Naughty (서동현)))")
                        .genre("hip-hop")
                        .singer("GIRIBOY")
                        .difficultyAvg(3)
                        .playCount(141)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("pnaQ9CbE6P0")
                        .thumbnailPath("https://img.youtube.com/vi/pnaQ9CbE6P0/0.jpg")
                        .title("자우림 '스물다섯, 스물하나' 어쿠스틱커버 by 장범준 Acoustic COVER")
                        .genre("performance")
                        .singer("장범준")
                        .difficultyAvg(4)
                        .playCount(188574)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("YwC0m0XaD2E")
                        .thumbnailPath("https://img.youtube.com/vi/YwC0m0XaD2E/0.jpg")
                        .title("최고의 피카츄 월드컵 (※동심파괴 주의)")
                        .genre("idol")
                        .singer("침착맨")
                        .difficultyAvg(3)
                        .playCount(14124)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("aZCfbL5oIeI")
                        .thumbnailPath("https://img.youtube.com/vi/aZCfbL5oIeI/0.jpg")
                        .title("Eul (Feat. BIG Naughty) (을 (Feat. BIG Naughty (서동현)))")
                        .genre("hip-hop")
                        .singer("GIRIBOY")
                        .difficultyAvg(3)
                        .playCount(141)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("pnaQ9CbE6P0")
                        .thumbnailPath("https://img.youtube.com/vi/pnaQ9CbE6P0/0.jpg")
                        .title("자우림 '스물다섯, 스물하나' 어쿠스틱커버 by 장범준 Acoustic COVER")
                        .genre("performance")
                        .singer("장범준")
                        .difficultyAvg(4)
                        .playCount(188574)
                        .build()
        );
        videos.add(
                Video.builder()
                        .id("YwC0m0XaD2E")
                        .thumbnailPath("https://img.youtube.com/vi/YwC0m0XaD2E/0.jpg")
                        .title("최고의 피카츄 월드컵 (※동심파괴 주의)")
                        .genre("idol")
                        .singer("침착맨")
                        .difficultyAvg(3)
                        .playCount(14124)
                        .build()
        );
        return videos;
    }

}
