package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.chordplay.chordplayapiserver.domain.entity.item.Chord;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SheetChangeRequest {
    @Min(value = 0, message = "위치")
    private int position;
    private Chord chord;

    @Builder
    public SheetChangeRequest(int position, Chord chord) {
        this.position = position;
        this.chord = chord;
    }
}
