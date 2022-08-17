package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@Slf4j
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping(value = "/search")
    public ApiResponse<List<VideoResponse>> search(@RequestParam String search_query){
        log.info("search: "+ search_query);
        List<VideoResponse> videoResponses = videoService.search(search_query);
        return ApiResponse.success(videoResponses, 200);
    }


}
