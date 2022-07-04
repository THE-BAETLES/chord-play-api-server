package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "MY_COLLECTION")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class MyCollection {
    @Id
    private String id;
    private Video video;
    private Timestamp last_played;
}
