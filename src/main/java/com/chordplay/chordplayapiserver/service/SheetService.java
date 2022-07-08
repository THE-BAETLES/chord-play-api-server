package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.dto.sheet.SheetDataResponseDTO;

public interface SheetService {
    SheetDataResponseDTO read(String id);

}
