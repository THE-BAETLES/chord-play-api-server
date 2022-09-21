package com.chordplay.chordplayapiserver.acceptance.sheet;


import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.sheet.step.SheetTestStep;
import com.chordplay.chordplayapiserver.acceptance.video.step.VideoTestStep;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("악보 관련 기능")
public class SheetAcceptanceTest extends AcceptanceTest {
    
//    @Test
//    @DisplayName("악보 생성 후 빈 악보 데이터 생성")
//    void createSheetAndSheetData(){
//
//        //given
//        String videoId = "rG3FJePqJJQ";
//        VideoResponse videoResponse = VideoTestStep.비디오_생성하고_VideoResponse_객체로_가져오기(videoId);
//
//        //when
//        ExtractableResponse<Response> response = SheetTestStep.악보_생성_후_빈_악보_데이터_생성하기(videoId);
//
//        //then
//        SheetTestStep.악보와_빈_악보_데이터_생성_성공_검증하기(response, videoId);
//
//    }
}
