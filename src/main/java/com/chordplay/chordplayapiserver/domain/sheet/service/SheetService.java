package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.sheet.SheetDataResponseDTO;

public interface SheetService {
    SheetDataResponseDTO read(String id);

}
