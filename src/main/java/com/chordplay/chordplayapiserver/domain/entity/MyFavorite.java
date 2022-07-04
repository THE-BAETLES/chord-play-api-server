package com.chordplay.chordplayapiserver.domain.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "MY_FAVORITE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MyFavorite {

    @Id
    private String id;
    private Video video;

    @Builder
    public MyFavorite(String id, Video video) {
        this.id = id;
        this.video = video;
    }
}
