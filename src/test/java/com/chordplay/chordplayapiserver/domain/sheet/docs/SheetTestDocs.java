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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;

public class SheetTestDocs {

    public static final String nameOfDocsThatGetSheet = "get_a_sheet";
    public static final String nameOfDocsThatGetMySheets = "sheet/get_my_sheets";
    public static final String nameOfDocsThatGetSheetsOfMyLike = "sheet/get_sheets_of_my_like";

    public static final String nameOfDocsThatDeleteSheet = "delete_a_sheet";
    public static final String nameOfDocsThatGetSheetData = "get_a_sheet_data";
    public static final String getNameOfDocsThatGetSheetsByVideoId = "get_sheets_by_video_id";
    public static final String getNameOfDocsThatGetSharedSheets = "get_shared_sheets";
    public static final String getNameOfDocsThatCreateAiSheet = "create_ai_sheet";
    public static final String getNameOfDocsThatUpdateSheetChord = "sheet/update_sheet_chord";
    public static final String getNameOfDocsThatDuplicateSheetChord = "sheet/duplicate_a_sheet";

    public static RestDocumentationResultHandler documentOnGettingSheet() {
        return document(nameOfDocsThatGetSheet,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                ),
                responseFields(CommonTestDocs.commonDocs()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields()));
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
                responseFields(CommonTestDocs.commonDocs()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields()));
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
                responseFields(CommonTestDocs.commonDocs()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetDataResponseFields()));
    }

    public static ResultHandler documentOnGettingSheetsByVideoId() {
        return document(getNameOfDocsThatGetSheetsByVideoId,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(parameterWithName("videoId").description("비디오 ID")),
                responseFields(CommonTestDocs.commonDocs())
                        .andWithPrefix("data.", getSheetsFieldsByResponse()),
                responseFields(beneathPath("data.my[]").withSubsectionId("data"),getSheetResponseFields()));


    }

    public static ResultHandler documentOnGetSharedSheets() {
        return document(getNameOfDocsThatGetSharedSheets,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                requestParameters(parameterWithName("videoId").description("비디오 ID")),
                responseFields(CommonTestDocs.commonDocsOfArray()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields()));


                        //.andWithPrefix("data[0].", getSheetResponseFields()));
//                responseFields(CommonTestDocs.commonDocsOfArray())
//                        .andWithPrefix("data[0].", getSheetResponseFields()));
    }

    public static ResultHandler documentOnCreatingAiSheet() {

        return document(getNameOfDocsThatCreateAiSheet,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("videoId").description("비디오 아이디")
                )

        );
    }

    public static ResultHandler documentOnUpdatingSheetChord() {
        return document(getNameOfDocsThatUpdateSheetChord,
                getDocumentRequest(),
                getDocumentResponse(),
                requestBody(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                )
        );
    }

    public static ResultHandler documentOnDuplicatingSheet() {
        return document(getNameOfDocsThatDuplicateSheetChord,
                getDocumentRequest(),
                getDocumentResponse(),
                requestBody(),
                requestFields(
                        fieldWithPath("sheet_id").type(JsonFieldType.STRING).description("악보 아이디"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("악보 제목 (입력하지 않을 시 'no title'로 저장됨")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")
                ),
                responseFields(CommonTestDocs.commonDocs()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields())
        );
    }

    public static RestDocumentationResultHandler documentOnGettingSheetsOfMyLike() {
        return document(nameOfDocsThatGetSheetsOfMyLike,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                responseFields(CommonTestDocs.commonDocsOfArray()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields()));
    }

    public static RestDocumentationResultHandler documentOnGettingMySheets() {
        return document(nameOfDocsThatGetMySheets,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                responseFields(CommonTestDocs.commonDocsOfArray()),
                responseFields(beneathPath("data").withSubsectionId("data"),getSheetResponseFields()));
    }

    private static List<FieldDescriptor> getSheetResponseFields(){

        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("video_id").type(JsonFieldType.STRING).description("악보의 비디오 ID"),
                fieldWithPath("user_id").type(JsonFieldType.STRING).description("악보의 저자 ID"),
                fieldWithPath("user_nickname").type(JsonFieldType.STRING).description("악보의 저자 닉네임"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("악보 이름"),
                fieldWithPath("created_at").type(JsonFieldType.STRING).description("악보 생성 날짜"),
                fieldWithPath("updated_at").type(JsonFieldType.STRING).description("악보 최종 수정 날짜"),
                fieldWithPath("like_count").type(JsonFieldType.NUMBER).description("악보의 좋아요 개수"),
                fieldWithPath("liked").type(JsonFieldType.BOOLEAN).description("내가 좋아요를 누른 악보인지")
        );
    }

    private static List<FieldDescriptor> getSheetDataResponseFields(){

        return Arrays.asList(
                fieldWithPath("_id").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("bpm").type(JsonFieldType.NUMBER).description("악보의 속도"),
                fieldWithPath("infos").type(JsonFieldType.ARRAY).description("박자 객체"),
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("생성 상태"),
                fieldWithPath("infos[0].chord.root").type(JsonFieldType.STRING).description("코드의 root"),
                fieldWithPath("infos[0].chord.triad").type(JsonFieldType.STRING).description("코드의 triad"),
                fieldWithPath("infos[0].chord.bass").type(JsonFieldType.STRING).description("코드의 bass"),
                fieldWithPath("infos[0].beat_time").type(JsonFieldType.NUMBER).description("해당 박자의 시간(sec 기준)")
        );
    }

    private static List<FieldDescriptor> getSheetsFieldsByResponse(){
        return Arrays.asList(
                fieldWithPath("my").type(JsonFieldType.ARRAY).description("내가 쓴 악보"),
                subsectionWithPath("shared").type(JsonFieldType.ARRAY).description("공유된 악보"),
                subsectionWithPath("like").type(JsonFieldType.ARRAY).description("내가 좋아요 누른 악보")
        );
    }



}
