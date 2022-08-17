package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@Slf4j
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<VideoResponse>> search(@RequestParam String search_query){
        log.info("search: "+ search_query);
        List<VideoResponse> videoResponses = videoService.search(search_query);
        return ResponseEntity.ok(videoResponses);
    }


}
