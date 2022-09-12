package com.chordplay.chordplayapiserver.domain.sheet.docs;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class SheetTestDocs {

    public static final String nameOfDocsThatGetSheet = "get_a_sheet";
    public static final String nameOfDocsThatDeleteSheet = "delete_a_sheet";
    public static final String nameOfDocsThatGetSheetData = "get_a_sheet_data";


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

    public static ResultHandler documentOnDeleteingSheet() {
        return document(nameOfDocsThatDeleteSheet,
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

    public static ResultHandler documentOnGetSheetData() {
        return document(nameOfDocsThatGetSheetData,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                ),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.", getSheetDataResponseFields()));
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

    private static List<FieldDescriptor> getSheetDataResponseFields(){

        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("bpm").type(JsonFieldType.NUMBER).description("악보의 속도"),
                fieldWithPath("chord_infos").type(JsonFieldType.ARRAY).description("음표"),
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("악보 생성 완료 상태"),
                fieldWithPath("chord_infos[0].start").type(JsonFieldType.NUMBER).description("코드 구간 시작 시간"),
                fieldWithPath("chord_infos[0].end").type(JsonFieldType.NUMBER).description("코드 구간 끝 시간"),
                fieldWithPath("chord_infos[0].position").type(JsonFieldType.NUMBER).description("악보 상 코드 위치"),
                fieldWithPath("chord_infos[0].chord").type(JsonFieldType.STRING).description("코드")
        );
    }


}
