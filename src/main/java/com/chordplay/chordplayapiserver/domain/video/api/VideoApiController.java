package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.WatchHistoryResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@Slf4j
public class VideoApiController{

    private final VideoService videoService;

    @GetMapping(value = "/search")
    public ApiResponse<List<VideoResponse>> search(@RequestParam String searchTitle){
        log.info("search: "+ searchTitle);
        List<VideoResponse> videoResponses = videoService.search(searchTitle);
        return ApiResponse.success(videoResponses, 200);
    }

    @GetMapping(value = "/watch-history")
    public ApiResponse<List<WatchHistoryResponse>> search(@RequestParam int offset,
                                                   @RequestParam int limit){
        List<WatchHistoryResponse> watchHistoryResponses = videoService.getWatchHistory(offset,limit);
        return ApiResponse.success(watchHistoryResponses, 200);
    }
    @PostMapping("/{videoId}")
    public ApiResponse<VideoResponse> createVideo(@PathVariable("videoId") String videoId){
        Video video = videoService.create(videoId);
        VideoResponse videoResponse = new VideoResponse(video);
        return ApiResponse.success(videoResponse, 201);
    }

    @GetMapping("/{videoId}")
    public ApiResponse<VideoResponse> getVideo(@PathVariable("videoId") String videoId){
        Video video = videoService.get(videoId);
        VideoResponse videoResponse = new VideoResponse(video);
        return ApiResponse.success(videoResponse, 201);
    }
}
