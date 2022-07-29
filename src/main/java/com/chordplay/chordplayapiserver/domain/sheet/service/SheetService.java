package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;

public interface SheetService {
    void createAi(SheetAiRequest dto);
    SheetDataResponse read(String id);

}
