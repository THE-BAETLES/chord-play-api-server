package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetDataRequest {
    private String sheetId;

    public SheetDataRequest(String sheetId) {
        this.sheetId = sheetId;
    }
}
