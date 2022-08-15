package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SheetService {
    SseEmitter createSheet(SheetAiRequest req);
    SheetData getSheetData(String sheetId);

}
