package com.chordplay.chordplayapiserver.domain.sheet.dto.sheet;

import lombok.Getter;

@Getter
public class SheetDataRequestDTO {
    private String sheetId;

    public SheetDataRequestDTO(String sheetId) {
        this.sheetId = sheetId;
    }
}
