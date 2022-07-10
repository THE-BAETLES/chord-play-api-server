package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class SheetDataResponse {

    private int bpm;
    private List<ChordInfo> ChordInfos;

    public SheetDataResponse(SheetData sheetData) {
        this.bpm = sheetData.getBpm();
        ChordInfos = sheetData.getChordInfos();
    }
}
