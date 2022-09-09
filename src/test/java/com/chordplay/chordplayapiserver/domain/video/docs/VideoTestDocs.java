package com.chordplay.chordplayapiserver.domain.video.docs;

import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;
import java.util.List;

import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentRequest;
import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class VideoTestDocs {

    public static RestDocumentationResultHandler documentOnVideo (String name){
        return document(name, // (4)
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("videoId").description("아이디")
                ),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.",videoResponse()));
    }

    private static List<FieldDescriptor> videoResponse(){
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
                fieldWithPath("sheet_count").type(JsonFieldType.NUMBER).description("악보 조회수")
        );
    }


}
