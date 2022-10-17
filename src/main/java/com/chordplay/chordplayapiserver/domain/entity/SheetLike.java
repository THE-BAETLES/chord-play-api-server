package com.chordplay.chordplayapiserver.domain.entity;

import com.mongodb.internal.connection.Time;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;

import java.time.LocalDateTime;

@Document(collection = "SHEET_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SheetLike {

    @Id
    private String id;
    @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    private Sheet sheet;

    private LocalDateTime time;

    @Builder
    public SheetLike(String id, User user, Sheet sheet, LocalDateTime time) {
        this.id = id;
        this.user = user;
        this.sheet = sheet;
        this.time = time;
    }

    public SheetLike(User user, Sheet sheet, LocalDateTime time) {
        this.id = id;
        this.user = user;
        this.sheet = sheet;
        this.time = time;
    }
}
