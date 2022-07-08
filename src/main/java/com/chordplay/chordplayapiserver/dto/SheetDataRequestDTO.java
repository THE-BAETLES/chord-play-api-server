package com.chordplay.chordplayapiserver.dto;

import lombok.Getter;

@Getter
public class SheetDataRequestDTO {
    private String sheetId;

    public SheetDataRequestDTO(String sheetId) {
        this.sheetId = sheetId;
    }
}
