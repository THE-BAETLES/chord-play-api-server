package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SheetService {
    SseEmitter createAi(SheetAiRequest dto);
    SheetDataResponse read(String id);

}
