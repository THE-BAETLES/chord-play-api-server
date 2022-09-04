package com.chordplay.chordplayapiserver.acceptance.video;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.video.step.VideoTestStep;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("비디오 관련 기능")
public class VideoAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("유튜브에서 곡 검색하기")
    void searchVideo() {
        //given
        String searchTitle = "장범준";

        //when
        ExtractableResponse<Response> response = VideoTestStep.비디오_검색하기(searchTitle);

        //then
        VideoTestStep.비디오_검색_성공_검증하기(response);

    }

    @Test
    @DisplayName("특정 video 정보 생성하고 생성되었을 시 가져오기")
    void createVideo(){
    
        //given
        String searchTitle = "장범준";
        VideoResponse firstVideo = VideoTestStep.비디오_검색하고_첫_video_가져오기(searchTitle);
        String videoId = firstVideo.getId();

        //when
        ExtractableResponse<Response> response = VideoTestStep.비디오_생성하고_가져오기(videoId);

        //then
        VideoTestStep.비디오_생성하고_가져오기_성공_검증하기(response, firstVideo);
    }

    @Test
    @DisplayName("특정 video 조회하기")
    void getVideo(){

        //given
        String searchTitle = "장범준";
        VideoResponse firstVideo = VideoTestStep.유튜브에서_비디오_검색하고_첫_video_가져오기(searchTitle);
        String videoId = firstVideo.getId();
        ExtractableResponse<Response> createResponse = VideoTestStep.비디오_생성하고_가져오기(videoId);

        //when


        //then

    }

    @Test
    @DisplayName("Watch history 가져오기")
    void getWatchHistory(){
        //given
        // 특정 user가 특정 비디오의 Sheet Data를 가져온다

        //when
        //유저의 watch history를 가져온다

        //then
        //유저가 watch한 비디오가 불러온 watch history에 있다
        //play_count가 1 올랐다

    }


}
