package com.chordplay.chordplayapiserver.domain.sheetLike.docs;

import com.chordplay.chordplayapiserver.global.docs.CommonTestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentRequest;
import static com.chordplay.chordplayapiserver.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class SheetLikeTestDocs {

    private static final String nameOfDocsThatAddSheetLike = "sheet-like/add_sheet_like";
    private static final String nameOfDocsThatDeleteSheetLike = "sheet-like/delete_sheet_like";

    public static RestDocumentationResultHandler documentOnAddSheetLike() {
        return document(nameOfDocsThatAddSheetLike,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                ),
                responseFields(CommonTestDocs.commonDocsOfEmpty()));
    }

    public static RestDocumentationResultHandler documentOnDeleteSheetLike() {
        return document(nameOfDocsThatDeleteSheetLike,
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer {token}")),
                pathParameters(
                        parameterWithName("sheetId").description("악보 아이디")
                ),
                responseFields(CommonTestDocs.commonDocsOfEmpty()));
    }
}
