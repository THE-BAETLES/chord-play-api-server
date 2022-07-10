package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;

public interface SheetService {
    SheetDataResponse read(String id);

}
