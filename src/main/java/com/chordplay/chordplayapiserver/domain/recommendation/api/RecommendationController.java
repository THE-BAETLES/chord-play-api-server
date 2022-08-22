package com.chordplay.chordplayapiserver.domain.recommendation.api;


import com.chordplay.chordplayapiserver.domain.recommendation.RecommendationService;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.exception.NotFullyJoinedException;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    @GetMapping
    public ApiResponse<List<VideoResponse>> getRecommendedVideos(@RequestParam int offset, @RequestParam int limit){
        List<VideoResponse> videoResponses = recommendationService.getRecommendedVideos(offset,limit);
        return ApiResponse.success(videoResponses, 200);
    }
}
