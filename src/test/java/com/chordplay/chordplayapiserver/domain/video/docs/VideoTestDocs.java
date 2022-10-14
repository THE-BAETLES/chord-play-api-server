package com.chordplay.chordplayapiserver.domain.video.docs;

import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;
import java.util.List;

import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentRequest;
import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class VideoTestDocs {


    private static final String nameOfDocsThatCreateVideo = "create_a_video";
    private static final String nameOfDocsThatGetVideo = "get_a_video";
    private static final String nameOfDocsThatSearchVideos = "search_videos";
    private static final String nameOfDocsThatGetGradeCollection = "get_grade_collection";
    private static final String nameOfDocsThatGetWatchHistory = "get_watch_history";
    public static RestDocumentationResultHandler documentOnCreatingVideo(){
        return document(nameOfDocsThatCreateVideo,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("videoId").description("아이디")
                ),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.",videoResponse()));
    }
    public static RestDocumentationResultHandler documentOnGettingVideo(){
        return document(nameOfDocsThatGetVideo,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("videoId").description("아이디")
                ),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.",videoResponse()));
    }
    public static RestDocumentationResultHandler documentOnVideoBySearching (String searchTitle) {
        return document(nameOfDocsThatSearchVideos,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(parameterWithName("searchTitle").description(searchTitle)),
                responseFields(CommonTestDocs.commonDocsOfArray())
                        .andWithPrefix("data[0].",videoResponse()));
    }

    public static RestDocumentationResultHandler documentOnGetGradeCollection (String performerGrade) {
        return document(nameOfDocsThatGetGradeCollection,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(parameterWithName("performerGrade").description("BEGINNER, INTERMEDIATE, EXPERT 를 선택할 수 있습니다")),
                responseFields(CommonTestDocs.commonDocsOfArray())
                        .andWithPrefix("data[0].",videoResponse()));
    }

    public static RestDocumentationResultHandler documentOnGetWatchHistory() {
        return document(nameOfDocsThatGetWatchHistory,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(
                        parameterWithName("offset").description("특정 위치부터 정보를 받아옵니다"),
                        parameterWithName("limit").description("특정 갯수만큼 정보를 받아옵니다")
                        ),
                responseFields(CommonTestDocs.commonDocsOfArray())
                        .andWithPrefix("data[0].",videoResponse()));
    }

    public static List<FieldDescriptor> videoResponse(){
        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("thumbnail_path").type(JsonFieldType.STRING).description("썸네일"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("genre").type(JsonFieldType.STRING).description("장르"),
                fieldWithPath("singer").type(JsonFieldType.STRING).description("가수"),
                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그"),
                fieldWithPath("length").type(JsonFieldType.NUMBER).description("영상 길이"),
                fieldWithPath("created_at").type(JsonFieldType.STRING).description("영상 게시일"),
                fieldWithPath("difficulty_avg").type(JsonFieldType.NUMBER).description("난이도"),
                fieldWithPath("play_count").type(JsonFieldType.NUMBER).description("영상 조회수"),
                fieldWithPath("sheet_count").type(JsonFieldType.NUMBER).description("악보 조회수")
        );
    }
}
