package com.chordplay.chordplayapiserver.acceptance.video;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.video.step.VideoTestStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("비디오 관련 기능")
public class VideoAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("유튜브에서 곡 검색하기")
    void searchVideo(){

        //given
        String searchTitle = "장범준";

        //when
        ExtractableResponse<Response> response = VideoTestStep.비디오_검색하기(searchTitle);
    
        //then
        VideoTestStep.비디오_검색_성공_검증하기(response);
        
    }
}
