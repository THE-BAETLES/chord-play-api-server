package com.chordplay.chordplayapiserver.acceptance.video.step;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.acceptance.video.dto.VideoTestResponse;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class VideoTestStep {

    public static ExtractableResponse<Response> 비디오_검색하기(String searchTitle){
        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .param("searchTitle", searchTitle)
                .when()
                .get("/videos/search")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 비디오_생성하고_가져오기(String videoId){
        return RestAssured
                .given().log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AcceptanceTest.getFirebaseToken())
                .when()
                .post("/videos/{videoId}",videoId)
                .then().log().all()
                .extract();
    }

    public static VideoResponse 비디오_검색하고_첫_video_가져오기(String searchTitle) {
        ExtractableResponse<Response> response = 비디오_검색하기(searchTitle);
        VideoResponse firstVideo = response.jsonPath().getObject("data[0]", VideoResponse.class);
        return firstVideo;
    }

    public static void 비디오_검색_성공_검증하기(ExtractableResponse<Response> response){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<VideoResponse> videoResponses = response.jsonPath().getList("data", VideoResponse.class);
        assertThat(videoResponses.size()).isNotEqualTo(0);
        for(VideoResponse v : videoResponses){
            assertThat(v.getTitle()).isNotNull();
        }
    }

    public static void 비디오_생성하고_가져오기_성공_검증하기(ExtractableResponse<Response> response, VideoResponse video){
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        VideoResponse receivedVideo = response.jsonPath().getObject("data", VideoResponse.class);
        assertThat(receivedVideo.equals(video)).isTrue();
    }

}
