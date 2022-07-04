package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "VIDEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Video {

    @Id
    private String id;

    @Builder
    public Video(String id, String thumnail_path, String vid_youtube, String title, String genre, String singer, int difficulty_avg, int play_count) {
        this.id = id;
        this.thumnail_path = thumnail_path;
        this.vid_youtube = vid_youtube;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.difficulty_avg = difficulty_avg;
        this.play_count = play_count;
    }

    private String thumnail_path;
    private String vid_youtube;
    private String title;
    private String genre;
    private String singer;
    private int difficulty_avg;
    private int play_count;

}
