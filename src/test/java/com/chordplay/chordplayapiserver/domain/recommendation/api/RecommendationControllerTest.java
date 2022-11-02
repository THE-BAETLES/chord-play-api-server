package com.chordplay.chordplayapiserver.domain.recommendation.api;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.recommendation.RecommendationService;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.video.api.VideoApiController;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import com.chordplay.chordplayapiserver.util.WithMockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentRequest;
import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("recommendation 컨트롤러 테스트")
@WebMvcTest(controllers = RecommendationController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@WithMockCustomUser
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.baetles.site")
class RecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RecommendationService recommendationService;

    static String nameOfDocsThatRecommendedVideos = "recommendation/get_recommended_videos";

    @Test
    @DisplayName("추천 비디오 ID 배열 가져오기_")
    public void getRecommendedVideosTest() throws Exception {


        //get
        List<VideoResponse> videoResponses = Arrays.asList(createMockVideoResponse(),createMockVideoResponse());
        given(recommendationService.getRecommendedVideos(0,videoResponses.size())).willReturn(videoResponses);

        //when
        ResultActions result = getRecommendedVideos(0, videoResponses.size());

        //then
        result = verifyOkAndVideos(result);
        result.andDo(documentOnRecommendingVideos());

    }

    private Video createMockVideoData(){
        return Video.builder()
                .id("RFMmK9E-cbc")
                .thumbnailPath("https://i.ytimg.com/vi/RFMmK9E-cbc/hqdefault.jpg")
                .title("Go Back (고백)")
                .genre("")
                .createdAt(LocalDateTime.parse("2021-07-19T09:05:32"))
                .length(210000)
                .singer("Jang Beom June - Topic")
                .tags(Arrays.asList("JANG BEOM JUNE", "장범준", "Go Back", "첫 번째 '고백'", "고백"))
                .build();
    }

    private VideoResponse createMockVideoResponse(){
        return new VideoResponse(createMockVideoData());
    }

    private ResultActions getRecommendedVideos(int offset, int limit) throws Exception {
        return mockMvc.perform(get("/recommendation")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}")
                .param("offset", Integer.toString(offset))
                .param("limit",Integer.toString(limit))
        );
    }

    private void verifyOK(ResultActions result) throws Exception {
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("success"));
    }
    private ResultActions verifyOkAndVideos(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.length()").value(2));
    }

    public RestDocumentationResultHandler documentOnRecommendingVideos(){

        return document(nameOfDocsThatRecommendedVideos,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(
                        parameterWithName("offset").description("추천 시작 index"),
                        parameterWithName("limit").description("추천 개수 제한 량")),
                responseFields(CommonTestDocs.commonDocsOfArray()),
                responseFields(beneathPath("data").withSubsectionId("data"),videoResponseFields()));
    }

    private List<FieldDescriptor> videoResponseFields(){
        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("thumbnail_path").type(JsonFieldType.STRING).description("썸네일"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("genre").type(JsonFieldType.STRING).description("장르"),
                fieldWithPath("singer").type(JsonFieldType.STRING).description("가"),
                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그"),
                fieldWithPath("length").type(JsonFieldType.NUMBER).description("영상 길이"),
                fieldWithPath("created_at").type(JsonFieldType.STRING).description("영상 게시일"),
                fieldWithPath("difficulty_avg").type(JsonFieldType.NUMBER).description("난이도"),
                fieldWithPath("play_count").type(JsonFieldType.NUMBER).description("영상 조회수"),
                fieldWithPath("sheet_count").type(JsonFieldType.NUMBER).description("악보 조회수"),
                fieldWithPath("is_in_my_collection").type(JsonFieldType.BOOLEAN).description("내 곡 목록에 담았는지")
        );
    }
}