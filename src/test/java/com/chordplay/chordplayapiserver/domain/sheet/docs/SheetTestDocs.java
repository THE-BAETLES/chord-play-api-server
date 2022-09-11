package com.chordplay.chordplayapiserver.domain.sheet.docs;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
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

public class SheetTestDocs {

    public static final String nameOfDocsThatGetSheet = "get_a_sheet";

    public static RestDocumentationResultHandler documentOnGettingSheet() {
        return document(nameOfDocsThatGetSheet,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                ),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.", getSheetResponseFields()));
    }

    private static List<FieldDescriptor> getSheetResponseFields(){

        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("video_id").type(JsonFieldType.STRING).description("악보의 비디오 ID"),
                fieldWithPath("user_id").type(JsonFieldType.STRING).description("악보의 저자 ID"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("악보 이름"),
                fieldWithPath("created_at").type(JsonFieldType.STRING).description("악보 생성 날짜"),
                fieldWithPath("updated_at").type(JsonFieldType.STRING).description("악보 최종 수정 날짜"),
                fieldWithPath("like_count").type(JsonFieldType.NUMBER).description("악보의 좋아요 개수")
        );
    }

}
