package com.chordplay.chordplayapiserver.domain.user.docs;

import com.chordplay.chordplayapiserver.domain.user.dto.MyInformationResponse;
import com.chordplay.chordplayapiserver.domain.video.docs.VideoTestDocs;
import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.Arrays;
import java.util.List;

import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentRequest;
import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class UserTestDocs {
    private static String getNameOfDocsOfLoginSuccess = "login_success";
    private static String getNameOfDocsOfNicknameRecommendation = "get_nickname_recommendation";
    private static String getNameOfDocsOfNicknameDuplicationCheck = "post_nickname_duplication_check";
    private static String getNameOfDocsOfJoining = "post_join";
    private static String getNameOfDocsOfAddingVideoIdToMyCollection = "user/add_video_id_to_my_collection";
    private static String getNameOfDocsOfDeletingVideoIdFromMyCollection  = "user/delete_video_id_to_my_collection";
    private static String getNameOfDocsOfGettingMyCollection  = "user/get_my_collection";
    private static String getNameOfDocsOfGettingMyInformation  = "user/get_my_information";
    private static String getNameOfDocsOfGettingMyCollectionVideoIdList  = "user/get_my_collection_video_id_list";
    public static ResultHandler documentOnLoginSuccess() {
        return document(getNameOfDocsOfLoginSuccess,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                )
        );
    }

    public static ResultHandler documentOnMyInformation() {
        return document(getNameOfDocsOfGettingMyInformation,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                responseFields(CommonTestDocs.commonDocs()),
                responseFields(beneathPath("data").withSubsectionId("data"), MyInformationResponseFields())
        );
    }

    private static List<FieldDescriptor> MyInformationResponseFields() {
        return Arrays.asList(
                fieldWithPath("id").type(JsonFieldType.STRING).description("유저 아이디"),
                fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
                fieldWithPath("username").type(JsonFieldType.STRING).description("유저 이름"),
                fieldWithPath("firebase_uid").type(JsonFieldType.STRING).description("유저 firebase 아이디"),
                fieldWithPath("roles").type(JsonFieldType.STRING).description("유저 권한"),
                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 email"),
                fieldWithPath("country").type(JsonFieldType.STRING).description("유저 국가"),
                fieldWithPath("language").type(JsonFieldType.STRING).description("유저 언어"),
                fieldWithPath("gender").type(JsonFieldType.STRING).description("유저 성별"),
                fieldWithPath("performer_grade").type(JsonFieldType.STRING).description("유저 기타 숙련도"),
                fieldWithPath("membership").type(JsonFieldType.STRING).description("유저의 등급"),
                fieldWithPath("signup_favorite").type(JsonFieldType.ARRAY).description("유저의 초기 선택곡 리스트"),
                fieldWithPath("my_collection").type(JsonFieldType.ARRAY).description("유저의 내 곡 목록 ")

        );

    }

    public static ResultHandler documentOnNicknameRecommendation() {
        return document(getNameOfDocsOfNicknameRecommendation,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.", fieldWithPath("nickname").type(JsonFieldType.STRING).description("추천받은 닉네임"))
        );
    }

    public static ResultHandler documentOnNicknameDuplicationCheck() {
        return document(getNameOfDocsOfNicknameDuplicationCheck,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                requestBody()
        );
    }

    public static ResultHandler documentOnJoining() {
        return document(getNameOfDocsOfJoining,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                requestBody()
        );
    }

    public static ResultHandler documentOnAddingVideoIdToMyCollection() {
        return document(getNameOfDocsOfAddingVideoIdToMyCollection,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                pathParameters(
                        parameterWithName("videoId").description("비디오 아이디")
                )
        );
    }

    public static ResultHandler documentOnDeletingVideoIdFromMyCollection() {
        return document(getNameOfDocsOfDeletingVideoIdFromMyCollection,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                pathParameters(
                        parameterWithName("videoId").description("비디오 아이디")
                )
        );
    }

    public static ResultHandler documentOnGettingMyCollection() {
        return document(getNameOfDocsOfGettingMyCollection,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                responseFields(CommonTestDocs.commonDocsOfArray()),
                responseFields(beneathPath("data").withSubsectionId("data"),VideoTestDocs.videoResponse())
        );
    }

    public static ResultHandler documentOnGettingMyCollectionVideoIdList() {
        return document(getNameOfDocsOfGettingMyCollectionVideoIdList,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                responseFields(Arrays.asList(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                        subsectionWithPath("data").type(JsonFieldType.ARRAY).description("my collection의 video id만 가져온 배열")
                ))
        );
    }
}
