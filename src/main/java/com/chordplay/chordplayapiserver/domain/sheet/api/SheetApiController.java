package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sheets")
@RequiredArgsConstructor
@Slf4j
public class SheetApiController {
    private final SheetService sheetService;
    private final NotificationService notificationService;
    @PostMapping("/ai")
    public SseEmitter sheetAi(@RequestBody SheetAiRequest req){
        return sheetService.createSheetProcess(req);
    }

    @GetMapping(value = "/data/{sheetId}")
    public SheetDataResponse getSheetData(@PathVariable("sheetId") String sheetId){
        SheetData sheetData = sheetService.getSheetData(sheetId);
        return new SheetDataResponse(sheetData);
    }
    @DeleteMapping(value = "/{sheetId}")
    public SheetResponse deleteSheetAndSheetData(@PathVariable("sheetId") String sheetId){
        Sheet sheet = sheetService.deleteSheetAndSheetData(sheetId);
        return new SheetResponse(sheet);
    }
}
