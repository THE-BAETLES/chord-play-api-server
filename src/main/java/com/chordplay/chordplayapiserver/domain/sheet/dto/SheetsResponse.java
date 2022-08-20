package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetsResponse {

    List<SheetResponse> sharedSheet;
    List<SheetResponse> likeSheet;
    List<SheetResponse> mySheet;

    @Builder
    public SheetsResponse(List<Sheet> sharedSheet, List<Sheet> likeSheet, List<Sheet> mySheet) {
        this.sharedSheet = new ArrayList<SheetResponse>();
        this.likeSheet = new ArrayList<SheetResponse>();
        this.mySheet = new ArrayList<SheetResponse>();

        for(Sheet s : sharedSheet){
            this.sharedSheet.add(new SheetResponse(s));
        }
        for(Sheet s : likeSheet){
            this.sharedSheet.add(new SheetResponse(s));
        }
        for(Sheet s : mySheet){
            this.sharedSheet.add(new SheetResponse(s));
        }

    }
}
