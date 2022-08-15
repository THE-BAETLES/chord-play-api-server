package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
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
        return sheetService.createSheet(req);
    }

    @GetMapping(value = "/ai/{videoId}")
    public SseEmitter sheetAi_test(@PathVariable("videoId") String videoId){
        return sheetService.createSheet(new SheetAiRequest(videoId, 0));
    }

    @GetMapping(value = "/{sheetId}/data")
    public SheetDataResponse getSheetData(@PathVariable("sheetId") String sheetId){
        SheetData sheetData = sheetService.getSheetData(sheetId);
        return new SheetDataResponse(sheetData);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SheetDataResponse> sheet(@PathVariable("id") String id){
        //추후 구현예정
        return ResponseEntity.ok(null);
    }

//    @GetMapping(value = "/subscribe/{video_id}", produces = "text/event-stream")
//    public SseEmitter subscribe(@PathVariable String video_id) {
//        log.info(video_id + " was subscribed");
//        return notificationService.subscribe("user", video_id);
//    }

    @PostMapping("publish")
    public void publish(@RequestBody AiStatusMessage aiStatusMessage) throws JsonProcessingException {
        notificationService.publish(aiStatusMessage);
    }

}
