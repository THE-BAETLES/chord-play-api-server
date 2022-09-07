package com.chordplay.chordplayapiserver.acceptance.sheet.step;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
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

    public static void 악보와_빈_악보_데이터_생성_성공_검증하기(ExtractableResponse<Response> response, String videoId){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        SheetResponse sheetResponse = response.jsonPath().getObject("data.sheet", SheetResponse.class);
        SheetDataResponse sheetDataResponse = response.jsonPath().getObject("data.sheet_data", SheetDataResponse.class);

        String receivedVideoId = sheetResponse.getVideoId();
        assertThat(receivedVideoId).isEqualTo(videoId);
        assertThat(sheetDataResponse.getId()).isEqualTo(sheetResponse.getId());
    }
}
