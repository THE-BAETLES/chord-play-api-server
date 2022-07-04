package com.chordplay.chordplayapiserver.domain.entity;

import com.mongodb.internal.connection.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "LIKE")
@Getter
@RequiredArgsConstructor
public class Like {

    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy = true)
    private com.chordplay.chordplayapiserver.domain.sheet.entity.Sheet sheet;

    private Time time;
}
