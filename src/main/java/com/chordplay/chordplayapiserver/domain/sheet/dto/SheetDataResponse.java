package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetDataResponse {

    private int status;
    private int bpm;
    private List<ChordInfo> ChordInfos;

    public SheetDataResponse(SheetData sheetData) {
        this.bpm = sheetData.getBpm();
        ChordInfos = sheetData.getChordInfos();
        this.status = 3;
    }
}
