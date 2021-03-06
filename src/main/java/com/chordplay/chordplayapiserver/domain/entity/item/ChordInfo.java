package com.chordplay.chordplayapiserver.domain.entity.item;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
