package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<Void> sheetAi(@RequestBody SheetAiRequest dto){
        sheetService.createAi(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SheetDataResponse> sheet(@PathVariable("id") String id){
        System.out.println("sheet service: " + sheetService);

        SheetDataResponse responseDTO = sheetService.read(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/subscribe/{video_id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable String video_id,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        log.info(video_id + " was subscribed");
        return notificationService.subscribe("user", video_id, lastEventId);
    }



}
