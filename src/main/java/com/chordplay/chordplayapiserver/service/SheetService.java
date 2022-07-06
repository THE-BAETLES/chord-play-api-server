package com.chordplay.chordplayapiserver.service;

import com.chordplay.dto.SheetDataRequestDTO;
import com.chordplay.dto.SheetDataResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface SheetService {
    SheetDataResponseDTO read(String id);

}
