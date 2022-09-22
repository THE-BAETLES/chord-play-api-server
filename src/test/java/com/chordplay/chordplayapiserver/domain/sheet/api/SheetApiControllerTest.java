package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.chordplay.chordplayapiserver.domain.sheet.docs.SheetTestDocs;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetChangeRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetResponse;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetsResponse;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.video.api.VideoApiController;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
/*
 * @GetMapping("/ai/{videoId}")
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

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VideoService videoService;

    @MockBean
    SheetService sheetService;

    @MockBean
    NotificationService notificationService;


    @Test
    @DisplayName("sheet 정보 가져오기")
    public void getSheetTest() throws Exception {

        //get
        Sheet sheet = createMockSheet();
        given(sheetService.getSheet(sheet.getId())).willReturn(sheet);

        //when
        ResultActions result = getSheet(sheet.getId());

        //then
        result = verifyGettingSheet(result);
        result.andDo(SheetTestDocs.documentOnGettingSheet());
    }
    
    @Test
    @DisplayName("특정 비디오에 관련된 유저의 악보들을 가져오기")
    public void getSheetsByVideoIdTest() throws Exception {
        
        //get
        String videoId = "dinia_m0HGE";
        SheetsResponse sheetsResponse = createMockSheetResponses();
        given(sheetService.getSheetsByVideoId(videoId)).willReturn(sheetsResponse);

        //when
        ResultActions result = getSheetsByVideoId(videoId);

        //then
        result = verifyGettingSheetsByVideoId(result);
        result.andDo(SheetTestDocs.documentOnGettingSheetsByVideoId());
    }

    @Test
    @DisplayName("sheet and sheet data 삭제하기")
    public void deleteSheetAndSheetDataTest() throws Exception {
        
        //get
        Sheet sheet = createMockSheet();
        given(sheetService.deleteSheetAndSheetData(sheet.getId())).willReturn(sheet);

        //when
        ResultActions result = deleteSheetAndSheetData(sheet.getId());

        //then
        result = verifyDeletingSheet(result);
        result.andDo(SheetTestDocs.documentOnDeleteingSheet());
    }
    
    @Test
    @DisplayName("sheet data 가져오기")
    public void getSheetDataTest() throws Exception {

        //get
        SheetData sheetData = createMockSheetData();
        given(sheetService.getSheetData(sheetData.getId())).willReturn(sheetData);

        //when
        ResultActions result = getSheetData(sheetData.getId());

        //then
        result = verifyGetSheetData(result);
        result.andDo(SheetTestDocs.documentOnGetSheetData());
    }
    
    @Test
    @DisplayName("특정 비디오에 공유된 악보 목록 가져오기")
    public void getSharedSheetsTest() throws Exception {

        //get
        String videoId = "dinia_m0HGE";
        List<Sheet> sheets = createMockSheets();
        given(sheetService.getSharedSheets(videoId)).willReturn(sheets);

        //when
        ResultActions result = getSharedSheets(videoId);

        //then
        result = verifyGettingSharedSheets(result);
        result.andDo(SheetTestDocs.documentOnGetSharedSheets());
    }

    @Test
    @DisplayName("AI sheet 생성 요청하기_videoId_Status_응답")
    public void createAiSheet() throws Exception {

        //get
        final Long DEFAULT_TIMEOUT = 60L * 1000 * 5;
        String videoId = "dinia_m0HGE";
        List<Sheet> sheets = createMockSheets();
        given(sheetService.createSheetProcess(videoId)).willReturn(new SseEmitter(DEFAULT_TIMEOUT));

        //when
        ResultActions result = createAiSheet(videoId);

        //then
        result = verifyCreateAiSheet(result);
        result.andDo(SheetTestDocs.documentOnCreatingAiSheet());
    }

    @Test
    @DisplayName("악보데이터 코드 변경_정상 파라미터_정상 응답")
    public void updateSheetChordTest() throws Exception {

        //get
        String sheetId= "abc";
        SheetChangeRequest sheetChangeRequest = new SheetChangeRequest(3, "B");

        //when
        ResultActions result = updateSheetChord(sheetId, sheetChangeRequest);

        //then
        verifyOK(result);
        result.andDo(SheetTestDocs.documentOnUpdatingSheetChord());
    }

    @Test
    @DisplayName("악보데이터 코드 변경_비정상 파라미터_bad request 응답")
    public void updateSheetChordFailTest() throws Exception {

        //get
        String sheetId= "abc";
        SheetChangeRequest sheetChangeRequest = new SheetChangeRequest(-1, "B");

        //when
        ResultActions result = updateSheetChord(sheetId, sheetChangeRequest);

        //then
        result.andExpect(status().isBadRequest());
    }

    private Sheet createMockSheet(){

        User user = new User(ContextUtil.getPrincipalUserId());
        return Sheet.builder()
                .updatedAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .video(new Video("KZH-MpiwmaU"))
                .user(user)
                .title("Chord Play")
                .createdAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .id("6300d7e8aeeb0778c43ea37d")
                .build();
    }

    private SheetData createMockSheetData() {
        List<ChordInfo> chordInfos = new ArrayList<>();

        chordInfos.add(ChordInfo.builder()
                .chord("Am")
                .start(0.0)
                .end(2.23)
                .position(0).build());

        chordInfos.add(ChordInfo.builder()
                .chord("C")
                .start(2.23)
                .end(4.06)
                .position(1).build());

        return SheetData.builder()
                .bpm(123)
                .id("6300d7e8aeeb0778c43ea37d")
                .chordInfos(chordInfos).build();
    }

    private SheetsResponse createMockSheetResponses() {

        return SheetsResponse.builder()
                .likeSheet(Arrays.asList(createMockSheet()))
                .mySheet(Arrays.asList(createMockSheet()))
                .sharedSheet(Arrays.asList(createMockSheet())).build();
    }

    private List<Sheet> createMockSheets(){
        return Arrays.asList(createMockSheet());
    }
    private ResultActions getSheet(String sheetId) throws Exception {
        return mockMvc.perform(get("/sheets/{sheetId}",sheetId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions getSheetsByVideoId(String videoId) throws Exception {
        return mockMvc.perform(get("/sheets")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}")
                .param("videoId", videoId));
    }

    private ResultActions updateSheetChord(String sheetId, SheetChangeRequest content) throws Exception {
        return mockMvc.perform(patch("/sheets/data/{sheetId}",sheetId)
                .content(objectMapper.writeValueAsString(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions getSheetData(String sheetId) throws Exception {
        return mockMvc.perform(get("/sheets/data/{sheetId}",sheetId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions getSharedSheets(String videoId) throws Exception {
        return mockMvc.perform(get("/sheets/shared")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}")
                .param("videoId", videoId));
    }

    private ResultActions createAiSheet(String videoId) throws Exception {
        return mockMvc.perform(get("/sheets/ai/{videoId}",videoId)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .header("Authorization","Bearer {token}"));
    }


    private ResultActions deleteSheetAndSheetData(String sheetId) throws Exception {
        return mockMvc.perform(delete("/sheets/{sheetId}",sheetId)
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
    private ResultActions verifyGettingSheet(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.title").value("Chord Play"));
    }
    private ResultActions verifyGettingSheetsByVideoId(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.my[0].title").value("Chord Play"))
                .andExpect(jsonPath("$.data.like[0].title").value("Chord Play"))
                .andExpect(jsonPath("$.data.shared[0].title").value("Chord Play"));
    }

    private ResultActions verifyDeletingSheet(ResultActions result) throws Exception {
        verifyOK(result);
        return result;
    }

    private ResultActions verifyGetSheetData(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.bpm").value(123));
    }

    private ResultActions verifyGettingSharedSheets(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data[0].title").value("Chord Play"));
    }

    private ResultActions verifyCreateAiSheet(ResultActions result) throws Exception {
        return result.andExpect(status().isOk());
    }


}