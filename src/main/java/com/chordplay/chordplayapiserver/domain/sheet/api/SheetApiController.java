package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.*;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
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
        return sheetService.createSheetProcess(req.getVideoId());
    }
    @GetMapping("/ai/{videoId}")
    public SseEmitter sheetAi(@PathVariable String videoId){
        return sheetService.createSheetProcess(videoId);
    }

    @GetMapping(value = "/data/{sheetId}")
    public ApiResponse<SheetDataResponse> getSheetData(@PathVariable("sheetId") String sheetId){
        SheetData sheetData = sheetService.getSheetData(sheetId);
        return ApiResponse.success(new SheetDataResponse(sheetData),200);
    }

    @GetMapping("/{sheetId}")
    public ApiResponse<SheetResponse> getSheet(@PathVariable("sheetId") String sheetId){
        Sheet sheet = sheetService.getSheet(sheetId);
        return ApiResponse.success(new SheetResponse(sheet),200);
    }

    @GetMapping()
    public ApiResponse<SheetsResponse> getSheetByVideoId(@RequestParam String videoId){
        SheetsResponse sheetsResponse = sheetService.getSheetsByVideoId(videoId);
        return ApiResponse.success(sheetsResponse,200);
    }
    @DeleteMapping(value = "/{sheetId}")
    public ApiResponse<SheetResponse> deleteSheetAndSheetData(@PathVariable("sheetId") String sheetId){
        Sheet sheet = sheetService.deleteSheetAndSheetData(sheetId);
        return ApiResponse.success(new SheetResponse(sheet),200);
    }
}
