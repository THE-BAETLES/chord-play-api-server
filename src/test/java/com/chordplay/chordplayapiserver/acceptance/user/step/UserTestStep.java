package com.chordplay.chordplayapiserver.acceptance.user.step;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
public class UserTestStep {
    public static ExtractableResponse<Response> 닉네임_추천_받기(){
        return RestAssured
                        .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                        .when()
                        .get("/user/nickname")
                        .then().log().all()
                        .extract();
    }

    public static ExtractableResponse<Response> 닉네임_중복체크_조회하기(String nickname){
        Map<String, String> body = new HashMap<>();
        body.put("nickname", nickname);

        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .body(body)
                .when()
                .post("/user/check-duplication")
                .then().log().all()
                .extract();
    }

    public static void 닉네임_추천_성공_검증하기(ExtractableResponse<Response> response){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        String nickname = response.jsonPath().getString("data.nickname");
        ExtractableResponse<Response> duplicationResponse = 닉네임_중복체크_조회하기(nickname);
        닉네임_중복체크_성공_검증하기(duplicationResponse);
    }

    public static void 닉네임_중복체크_성공_검증하기(ExtractableResponse<Response> response){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
