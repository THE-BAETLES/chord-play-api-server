package com.chordplay.chordplayapiserver.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "MY_FAVORITE")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class MyFavorite {

    @Id
    private String id;
    private Video video;
}
