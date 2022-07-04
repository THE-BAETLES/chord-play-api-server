package com.chordplay.chordplayapiserver.domain.entity;

import com.mongodb.internal.connection.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import com.chordplay.chordplayapiserver.domain.sheet.entity.SheetInfo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Timestamp;

@Document(collection = "LIKE")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Like {

    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User user;
    @DocumentReference(lazy = true)
    private SheetInfo sheetInfo;

    private Time time;
}
