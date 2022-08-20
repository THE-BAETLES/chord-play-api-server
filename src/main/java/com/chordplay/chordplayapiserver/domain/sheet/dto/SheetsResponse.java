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

    List<SheetResponse> shared;
    List<SheetResponse> like;
    List<SheetResponse> my;

    @Builder
    public SheetsResponse(List<Sheet> sharedSheet, List<Sheet> likeSheet, List<Sheet> mySheet) {
        this.shared = new ArrayList<SheetResponse>();
        this.like = new ArrayList<SheetResponse>();
        this.my = new ArrayList<SheetResponse>();

        for(Sheet s : sharedSheet){
            this.shared.add(new SheetResponse(s));
        }
        for(Sheet s : likeSheet){
            this.shared.add(new SheetResponse(s));
        }
        for(Sheet s : mySheet){
            this.shared.add(new SheetResponse(s));
        }

    }
}
