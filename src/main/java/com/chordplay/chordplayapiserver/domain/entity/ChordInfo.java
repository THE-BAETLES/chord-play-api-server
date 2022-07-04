package com.chordplay.chordplayapiserver.domain.sheet.entity;

import lombok.Data;

@Data
public class ChordInfo {
    private String chord;
    private double start;
    private double end;
    private int position;
}
