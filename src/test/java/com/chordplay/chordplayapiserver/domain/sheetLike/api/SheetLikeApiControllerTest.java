package com.chordplay.chordplayapiserver.domain.sheetLike.api;

import com.chordplay.chordplayapiserver.domain.sheet.api.SheetApiController;
import com.chordplay.chordplayapiserver.domain.sheetLike.service.SheetLikeService;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.util.WithMockCustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Sheet-Like 컨트롤러 테스트")
@WebMvcTest(controllers = SheetLikeApiController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@WithMockCustomUser
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.baetles.site")
class SheetLikeApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SheetLikeService sheetLikeService;

    @Test
    @DisplayName("sheetlike 추가하기_sheetId_성공")
    public void addSheetLikeTest() throws Exception {
        //get
        String sheetId = "testSheetId";

        //when
        ResultActions result = addSheetLike(sheetId);

        //then
        verifyCreated(result);
    }

    @Test
    @DisplayName("sheetlike 삭제하기_sheetId_성공")
    public void deleteSheetLikeTest() throws Exception {
        //get
        String sheetId = "testSheetId";

        //when
        ResultActions result = deleteSheetLike(sheetId);

        //then
        verifyOK(result);
    }

    private ResultActions addSheetLike(String sheetId) throws Exception {
        return mockMvc.perform(post("/sheet-like/{sheetId}",sheetId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions deleteSheetLike(String sheetId) throws Exception {
        return mockMvc.perform(delete("/sheet-like/{sheetId}",sheetId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private void verifyOK(ResultActions result) throws Exception {
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("success"));
    }
    private void verifyCreated(ResultActions result) throws Exception {
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.message").value("success"));
    }

}