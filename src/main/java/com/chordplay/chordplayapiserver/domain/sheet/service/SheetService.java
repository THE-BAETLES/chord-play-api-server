package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface SheetService {
    SseEmitter createSheetProcess(String videoId);
    Sheet getSheet(String sheetId);
    SheetData getSheetData(String sheetId);

    Sheet createOnlySheet(String videoId);

    Sheet deleteSheetAndSheetData(String sheetId);

    SheetsResponse getSheetsByVideoId(String videoId);

    List<Sheet> getSharedSheets(String videoId);

    void updateSheetChord(String sheetId,SheetChangeRequest dto);

    void duplicateSheet(SheetDuplicationRequest dto);

}
