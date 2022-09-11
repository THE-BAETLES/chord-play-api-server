package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.sheet.docs.SheetTestDocs;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.video.api.VideoApiController;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
/*
 * @GetMapping("/ai/{videoId}")
 *  * @GetMapping()
 * @GetMapping(value = "/data/{sheetId}")
 * @GetMapping("/{sheetId}")
 * @GetMapping()
 * @DeleteMapping(value = "/{sheetId}")
 * @GetMapping("/shared")
 */

@DisplayName("Sheet 컨트롤러 테스트")
@WebMvcTest(controllers = SheetApiController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@WithMockCustomUser
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.baetles.site")
class SheetApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VideoService videoService;

    @MockBean
    SheetService sheetService;

    @MockBean
    NotificationService notificationService;


    @Test
    @DisplayName("sheet 정보 가져오기")
    public void getSheet() throws Exception {

        //get
        Sheet sheet = createMockSheet();
        given(sheetService.getSheet(sheet.getId())).willReturn(sheet);

        //when
        ResultActions result = getSheet(sheet.getId());

        //then
        result = verifyGettingSheet(result);
        result.andDo(SheetTestDocs.documentOnGettingSheet());
    }


    private Sheet createMockSheet(){

        User user = new User("ContextUtil.getPrincipalUserId()");
        return Sheet.builder()
                .updatedAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .video(new Video("KZH-MpiwmaU"))
                .user(user)
                .title("Chord Play")
                .createdAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .id("6300d7e8aeeb0778c43ea37d")
                .build();
    }

    private ResultActions getSheet(String sheetId) throws Exception {
        return mockMvc.perform(get("/sheets/{sheetId}",sheetId)
                .contentType(MediaType.APPLICATION_JSON)
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
    private ResultActions verifyGettingSheet(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.title").value("Chord Play"));
    }
}