package com.chordplay.chordplayapiserver.acceptance.user;


import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.user.step.UserTestStep;
import com.chordplay.chordplayapiserver.global.util.FirebaseUtil;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("회원가입/로그인 관련 기능")
public class UserAcceptanceTest extends AcceptanceTest {

    @Autowired
    FirebaseUtil firebaseLogin;

    //로그인 상태 체크
    @Test
    @DisplayName("닉네임 추천 받기")
    void recommendNickname(){

        //given

        //when
        ExtractableResponse<Response> response = UserTestStep.닉네임_추천_받기();

        //then
        UserTestStep.닉네임_추천_성공_검증하기(response);
    }

    @Test
    @DisplayName("닉네임 중복 체크 성공하기")
    void checkNicknameDuplication(){

        //given

        //when
        ExtractableResponse<Response> response =  UserTestStep.닉네임_중복체크_조회하기("bcwChordPlay");

        //then
        UserTestStep.닉네임_중복체크_성공_검증하기(response);
    }





}
