package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.item.Chord;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;

public interface SheetDataCustomRepository {
    void updateSheetChord(String sheetId, int position, Chord chord);
}
