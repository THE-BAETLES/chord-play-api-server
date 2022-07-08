package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.dto.SheetDataResponseDTO;

public interface SheetService {
    SheetDataResponseDTO read(String id);

}
