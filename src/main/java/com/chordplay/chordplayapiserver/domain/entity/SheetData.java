package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SHEET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SheetData {

    private int bpm;
    private List<ChordInfo> ChordInfo;

    @Builder
    public SheetData(int bpm, List<com.chordplay.chordplayapiserver.domain.entity.ChordInfo> chordInfo) {
        this.bpm = bpm;
        ChordInfo = chordInfo;
    }
}
