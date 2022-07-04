package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SHEET")
@Getter
@RequiredArgsConstructor
public class SheetData {

    private int bpm;
    private List<ChordInfo> ChordInfo;
}
