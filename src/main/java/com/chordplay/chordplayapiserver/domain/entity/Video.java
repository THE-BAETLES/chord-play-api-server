package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "VIDEO")
@Getter
@RequiredArgsConstructor
public class Video {

    @Id
    private String id;
    private String thumnail_path;
    private String vid_youtube;
    private String title;
    private String genre;
    private String singer;
    private Long difficulty_avg;
    private Long play_count;

}
