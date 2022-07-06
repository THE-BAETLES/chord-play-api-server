package com.chordplay.dto;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class SheetDataResponseDTO {

    private int bpm;
    private List<ChordInfo> ChordInfos;

    public SheetDataResponseDTO(SheetData sheetData) {
        this.bpm = sheetData.getBpm();
        ChordInfos = sheetData.getChordInfos();
    }
}
