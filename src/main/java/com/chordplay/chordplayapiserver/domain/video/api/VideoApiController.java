package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping(value = "/search/{search_query}")
    public ResponseEntity<List<VideoResponse>> search(@PathVariable("search_query") String search_query){
        System.out.println(search_query);
        List<VideoResponse> videoResponses = videoService.search(search_query);
        return ResponseEntity.ok(videoResponses);
    }

    @PostMapping("/{videoId}")
    public ResponseEntity<Void> create(@PathVariable("videoId") String videoId){
        videoService.create(videoId);
        return ResponseEntity.ok().build();
    }

}
