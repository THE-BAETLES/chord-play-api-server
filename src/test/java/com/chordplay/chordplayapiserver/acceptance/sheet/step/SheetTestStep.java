package com.chordplay.chordplayapiserver.acceptance.sheet.step;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SheetTestStep {
    
    
    
    public static ExtractableResponse<Response> 악보_생성_후_빈_악보_데이터_생성하기(String videoId){
        Map<String, String> body = new HashMap<>();
        body.put("video_id", videoId);

        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .body(body)
                .when()
                .post("/sheets")
                .then().log().all()
                .extract();
    }



    public static Sheet 악보_데이터_생성하기(User user){
        return Sheet.builder()
                .updatedAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .video(new Video("KZH-MpiwmaU"))
                .user(user)
                .title("Chord Play")
                .createdAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .id("6300d7e8aeeb0778c43ea37d")
                .build();
    }

    public static ExtractableResponse<Response> 내가_쓴_악보_목록_조회(){
        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .when()
                .get("/sheets/my")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 내가_좋아요_누른_악보_목록_조회(){
        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .when()
                .get("/sheets/my-like")
                .then().log().all()
                .extract();
    }

    public static void 악보와_빈_악보_데이터_생성_성공_검증하기(ExtractableResponse<Response> response, String videoId){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        SheetResponse sheetResponse = response.jsonPath().getObject("data.sheet", SheetResponse.class);
        SheetDataResponse sheetDataResponse = response.jsonPath().getObject("data.sheet_data", SheetDataResponse.class);

        String receivedVideoId = sheetResponse.getVideoId();
        assertThat(receivedVideoId).isEqualTo(videoId);
        assertThat(sheetDataResponse.getId()).isEqualTo(sheetResponse.getId());
    }

    public static void 내가_쓴_악보_목록_조회_검증하기(ExtractableResponse<Response> response, Sheet sheet){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<SheetResponse> sheetResponses = response.jsonPath().getList("data", SheetResponse.class);
        assertThat(sheetResponses.size()).isEqualTo(1);
        assertThat(sheetResponses.get(0).getId()).isEqualTo(sheet.getId());
    }

    public static void 내가_좋아요_누른_악보_목록_조회_검증하기(ExtractableResponse<Response> response, Sheet sheet){
        내가_쓴_악보_목록_조회_검증하기(response,sheet);
    }
}
