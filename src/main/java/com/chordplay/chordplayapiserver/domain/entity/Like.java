package com.chordplay.chordplayapiserver.domain.entity;

import com.mongodb.internal.connection.Time;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
@Document(collection = "LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {

    @Id
    private String id;
    @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    private Sheet sheet;

    private Time time;

    @Builder
    public Like(String id, User user, Sheet sheet, Time time) {
        this.id = id;
        this.user = user;
        this.sheet = sheet;
        this.time = time;
    }
}
