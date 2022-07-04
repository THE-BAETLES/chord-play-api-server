package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChordInfo {
    private String chord;
    private double start;
    private double end;
    private int position;
}
