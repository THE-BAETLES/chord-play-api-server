package com.chordplay.chordplayapiserver.acceptance.sheet;


import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.sheet.step.SheetTestStep;
import com.chordplay.chordplayapiserver.domain.dao.SheetLikeRepository;
import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Acceptance Test of sheetLike")
public class SheetAcceptanceTest extends AcceptanceTest {


    @Autowired
    SheetService sheetService;

    @Autowired
    SheetRepository sheetRepository;

    @Autowired
    SheetLikeRepository sheetLikeRepository;

    @Test
    @DisplayName("내가 쓴 악보 불러오기_성공")
    public void getMySheet(){

        //get
        User user = new User(getTestUserId());
        Sheet sheet = SheetTestStep.악보_데이터_생성하기(user);
        sheetRepository.save(sheet);

        //when
        ExtractableResponse<Response> response = SheetTestStep.내가_쓴_악보_목록_조회();

        //then
        SheetTestStep.내가_쓴_악보_목록_조회_검증하기(response,sheet);
    }

    @Test
    @DisplayName("내가 좋아요 누른 악보 불러오기_성공")
    public void getSheetOfMyLike() {

        //get
        User user = new User(getTestUserId());
        Sheet sheet = SheetTestStep.악보_데이터_생성하기(user);
        sheetRepository.save(sheet);
        sheetLikeRepository.save(new SheetLike(user, sheet));

        //when
        ExtractableResponse<Response> response = SheetTestStep.내가_좋아요_누른_악보_목록_조회();

        //then
        SheetTestStep.내가_좋아요_누른_악보_목록_조회_검증하기(response,sheet);
    }



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
