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
        return ApiResponse.success(videoResponses, HttpStatus.OK.value());
    }

    @GetMapping(value = "/watch-history")
    public ApiResponse<List<VideoResponse>> getWatchHistory(@RequestParam int offset,
                                                   @RequestParam int limit){
        List<VideoResponse> watchHistoryResponses = videoService.getWatchHistory(offset,limit);
        return ApiResponse.success(watchHistoryResponses, HttpStatus.OK.value());
    }
    @PostMapping("/{videoId}")
    public ResponseEntity<ApiResponse<VideoResponse>>  createVideo(@PathVariable("videoId") String videoId){
        Video video = videoService.create(videoId);
        VideoResponse videoResponse = new VideoResponse(video);
        ApiResponse<VideoResponse> body = ApiResponse.success(videoResponse, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/grade-collection")
    public ApiResponse<List<VideoResponse>> getGradeCollection(@RequestParam String performerGrade){
        List<VideoResponse> videoResponses = videoService.getGradeCollection(performerGrade);
        return ApiResponse.success(videoResponses, HttpStatus.OK.value());
    }

    @GetMapping("/{videoId}")
    public ApiResponse<VideoResponse> getVideo(@PathVariable("videoId") String videoId){
        Video video = videoService.get(videoId);
        VideoResponse videoResponse = new VideoResponse(video);
        return ApiResponse.success(videoResponse, HttpStatus.OK.value());
    }
}
