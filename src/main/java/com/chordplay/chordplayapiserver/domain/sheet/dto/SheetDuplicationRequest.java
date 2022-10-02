package com.chordplay.chordplayapiserver.domain.sheet.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetDuplicationRequest {
    @NotBlank(message = "코드")
    String sheetId;

    @Builder.Default
    String title = "no title";

    @Builder
    public SheetDuplicationRequest(String sheetId, String title) {
        this.sheetId = sheetId;
        this.title = title;
    }
}