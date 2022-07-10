package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;

public interface SheetGenerateService {
    void createAi(SheetAiRequest dto);

}
