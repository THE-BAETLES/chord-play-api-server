package com.chordplay.chordplayapiserver.domain.entity.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
public class Chord {

    private String root = "none";
    private String triad = "none";
    private String bass = "none";

    public Chord(String root, String triad, String bass) {
        this.root = root;
        this.triad = triad;
        this.bass = bass;
    }
}
