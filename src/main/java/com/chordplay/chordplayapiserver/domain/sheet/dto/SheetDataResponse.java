package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetDataResponse {


    @JsonProperty("_id")
    private String id;
    private int status;
    private int bpm;
    @JsonProperty("infos")
    private List<ChordInfo> chordInfos;

    public SheetDataResponse(SheetData sheetData) {
        this.id = sheetData.getId();
        this.bpm = sheetData.getBpm();
        this.chordInfos = sheetData.getChordInfos();
        this.status = 3;
    }
}
