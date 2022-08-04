package com.chordplay.chordplayapiserver.domain.entity;

import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SHEET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetData {

    @Id
    private String id;
    private int bpm;
    private List<ChordInfo> ChordInfos;

    @Builder
    public SheetData(String id, int bpm, List<ChordInfo> chordInfos) {
        this.id = id;
        this.bpm = bpm;
        ChordInfos = chordInfos;
    }
}
