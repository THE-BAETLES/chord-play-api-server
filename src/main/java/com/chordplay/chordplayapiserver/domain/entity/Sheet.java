package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.persistence.Entity;
import com.chordplay.chordplayapiserver.domain.entity.ChordInfo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SHEET")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Sheet {

    private int bpm;
    private List<ChordInfo> ChordInfo;
}
