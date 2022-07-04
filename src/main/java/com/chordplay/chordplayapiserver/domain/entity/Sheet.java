package com.chordplay.chordplayapiserver.domain.sheet.entity;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.Entity;

@Data
public class Sheet {
    @NonNull
    private int bpm;

    @NonNull
    private ChordInfo[] info;
}
