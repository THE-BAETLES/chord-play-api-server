package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "VIDEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Video {

    @Id
    private String id;

    @Builder
        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.genre = genre;
        this.singer = singer;
        this.difficultyAvg = difficultyAvg;
        this.playCount = playCount;
    }

    @Field(value = "thumbnail_path")
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    @Field(value = "difficulty_avg")
    private int difficultyAvg;

    @Field(value = "play_count")
    private int playCount;

    public Video(String id){
        this.id = id;
    }

}
