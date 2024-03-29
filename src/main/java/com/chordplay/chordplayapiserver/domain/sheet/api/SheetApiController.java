package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.*;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sheets")
@RequiredArgsConstructor
@Slf4j
public class SheetApiController {
    private final SheetService sheetService;
    private final NotificationService notificationService;

    @GetMapping("/ai/{videoId}")
    public SseEmitter sheetAi(@PathVariable String videoId){
        log.info("sse request : "+ videoId);
        return sheetService.createSheetProcess(videoId);

    }

    @GetMapping(value = "/data/{sheetId}")
    public ApiResponse<SheetDataResponse> getSheetData(@PathVariable("sheetId") String sheetId){
        SheetData sheetData = sheetService.getSheetData(sheetId);
        return ApiResponse.success(new SheetDataResponse(sheetData), HttpStatus.OK.value());
    }

    @GetMapping("/{sheetId}")
    public ApiResponse<SheetResponse> getSheet(@PathVariable("sheetId") String sheetId){
        Sheet sheet = sheetService.getSheet(sheetId);
        return ApiResponse.success(new SheetResponse(sheet),HttpStatus.OK.value());
    }

    @GetMapping()
    public ApiResponse<SheetsResponse> getSheetsByVideoId(@RequestParam String videoId){
        SheetsResponse sheetsResponse = sheetService.getSheetsByVideoId(videoId);
        return ApiResponse.success(sheetsResponse,HttpStatus.OK.value());
    }

    @DeleteMapping(value = "/{sheetId}")
    public ApiResponse<SheetResponse> deleteSheetAndSheetData(@PathVariable("sheetId") String sheetId){
        Sheet sheet = sheetService.deleteSheetAndSheetData(sheetId);
        return ApiResponse.success(new SheetResponse(sheet),HttpStatus.OK.value());
    }

    @GetMapping("/shared")
    public ApiResponse<List<SheetResponse>> getSharedSheets(@RequestParam String videoId){
        List<SheetResponse> sheetResponses = new ArrayList<>();
        List<Sheet> sharedSheets = sheetService.getSharedSheets(videoId);
        for ( Sheet s : sharedSheets ) {
            sheetResponses.add(new SheetResponse(s));
        }
        return ApiResponse.success(sheetResponses,HttpStatus.OK.value());
    }
    @PatchMapping("/data/{sheetId}")
    public ApiResponse<String> updateSheet(@PathVariable("sheetId") String sheetId
            , @RequestBody @Valid SheetChangeRequest dto){
        sheetService.updateSheetChord(sheetId, dto);
        return ApiResponse.success("",HttpStatus.OK.value());
    }

    @PostMapping("/duplication")
    public ResponseEntity<ApiResponse<SheetResponse>> duplicateSheet(@RequestBody @Valid SheetDuplicationRequest dto){
        SheetResponse sheetResponse = new SheetResponse(sheetService.duplicateSheet(dto));
        ApiResponse<SheetResponse> body = ApiResponse.success(sheetResponse, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
    @GetMapping("/my-like")
    public ApiResponse<List<SheetResponse>> getSheetsOfMyLike(){
        List<SheetResponse> sheetResponses = sheetService.getSheetsOfMyLike();
        return ApiResponse.success(sheetResponses,HttpStatus.OK.value());
    }

    @GetMapping("/my")
    public ApiResponse<List<SheetResponse>> getMySheet(){
        List<SheetResponse> sheetResponses = sheetService.getMySheets();
        return ApiResponse.success(sheetResponses,HttpStatus.OK.value());
    }



}
