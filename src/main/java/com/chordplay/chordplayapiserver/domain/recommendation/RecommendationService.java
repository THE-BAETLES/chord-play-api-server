package com.chordplay.chordplayapiserver.domain.recommendation;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.recommendation.dto.RecommendationResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.exception.RuntimeIoException;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private final WebClient webClient;
    private final VideoService videoService;


    public List<VideoResponse> getRecommendedVideos(int offset, int limit) {

        String userId = ContextUtil.getPrincipalUserId();
        RecommendationResponse recommendationResponse =  webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/recommendation/{userId}")
                        .queryParam("offset",offset)
                        .queryParam("limit",limit)
                        .build(userId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RecommendationResponse.class)
                .block(REQUEST_TIMEOUT);

        if (recommendationResponse.getPayload() == null) throw new RuntimeIoException();

        List<String> videoIdList = recommendationResponse.getPayload().getRecommendationList();
        List<VideoResponse> videoResponses = new ArrayList<>();
        for( String videoId : videoIdList ){
            Video video = videoService.create(videoId);
            VideoResponse videoResponse = videoService.toVideoResponse(video);
        }
        return videoResponses;
    }

}
