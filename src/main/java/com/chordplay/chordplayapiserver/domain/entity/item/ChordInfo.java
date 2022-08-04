package com.chordplay.chordplayapiserver.domain.entity.item;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChordInfo {
    @Builder
    public ChordInfo(String chord, double start, double end, int position) {
        this.chord = chord;
        this.start = start;
        this.end = end;
        this.position = position;
    }

    private String chord;
    private double start;
    private double end;
    private int position;
}
