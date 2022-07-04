package com.chordplay.chordplayapiserver.domain.sheet.entity;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import java.sql.Timestamp;

@Document(collection = "SHEET_INFO")
@Getter
@RequiredArgsConstructor
public class Sheet {
    @Id
    private String id;

    @DocumentReference(lazy = true)
    private Video video;

    @DocumentReference(lazy = true)
    private User user;

    private String title;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Long likeCount;
}
