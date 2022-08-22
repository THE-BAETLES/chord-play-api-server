package com.chordplay.chordplayapiserver.domain.recommendation;

import com.chordplay.chordplayapiserver.domain.recommendation.dto.RecommendationResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecommendationServiceTest {


    @Autowired RecommendationService recommendationService;
    @Test
    void test1(){
        List<VideoResponse> videoResponses = recommendationService.getRecommendedVideos(0,10);
        System.out.println(videoResponses);


    }
}