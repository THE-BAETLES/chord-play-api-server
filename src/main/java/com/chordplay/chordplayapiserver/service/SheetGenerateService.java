package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.dto.sheet.SheetAiRequest;

public interface SheetGenerateService {
    void createAi(SheetAiRequest dto);

}
