package com.chordplay.chordplayapiserver.domain.entity.item;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChordInfo {

    private Chord chord;
    private double beat_time;

    public ChordInfo(Chord chord, double beat_time) {
        this.chord = chord;
        this.beat_time = beat_time;
    }
}
