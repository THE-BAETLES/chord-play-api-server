package com.chordplay.chordplayapiserver.domain.entity;

import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SHEET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SheetData {

    private int bpm;
    private List<ChordInfo> ChordInfos;

    @Builder
    public SheetData(int bpm, List<ChordInfo> chordInfos) {
        this.bpm = bpm;
        ChordInfos = chordInfos;
    }
}
