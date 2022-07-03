package com.chordplay.chordplayapiserver.domain.sheet.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class ChordBlock {
    private String chord;
    private double start;
    private double end;
    private int position;
}
