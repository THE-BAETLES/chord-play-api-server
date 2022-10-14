package com.chordplay.chordplayapiserver.domain.user.docs;

import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultHandler;

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

    public static ResultHandler documentOnLoginSuccess() {
        return document(getNameOfDocsOfLoginSuccess,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                )
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
}
