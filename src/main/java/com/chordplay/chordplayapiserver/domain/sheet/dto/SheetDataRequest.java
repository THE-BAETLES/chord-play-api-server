package com.chordplay.chordplayapiserver.domain.sheet.dto;

import lombok.Getter;

@Getter
public class SheetDataRequest {
    private String sheetId;

    public SheetDataRequest(String sheetId) {
        this.sheetId = sheetId;
    }
}
