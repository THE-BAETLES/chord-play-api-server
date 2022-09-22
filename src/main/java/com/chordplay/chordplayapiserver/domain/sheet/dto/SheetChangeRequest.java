package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetChangeRequest {
    @Min(value = 0)
    private int position;
    @NotBlank
    private String chord;

    @Builder
    public SheetChangeRequest(int position, String chord) {
        this.position = position;
        this.chord = chord;
    }
}
