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
    public Video(String id, String thumbnail_path, String title, String genre, String singer, int difficulty_avg, int play_count) {
        this.id = id;
        this.thumbnail_path = thumbnail_path;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.difficulty_avg = difficulty_avg;
        this.play_count = play_count;
    }

    private String thumbnail_path;
    private String title;
    private String genre;
    private String singer;
    private int difficulty_avg;
    private int play_count;

    public Video(String id){
        this.id = id;
    }

}
