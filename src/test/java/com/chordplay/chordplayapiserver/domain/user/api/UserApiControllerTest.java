package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.sheet.api.SheetApiController;
import com.chordplay.chordplayapiserver.domain.sheet.docs.SheetTestDocs;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.user.docs.UserTestDocs;
import com.chordplay.chordplayapiserver.domain.user.dto.CheckDuplicationRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.NicknameResponse;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
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

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("user 컨트롤러 테스트")
@WebMvcTest(controllers = UserApiController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.baetles.site")
class UserApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("로그인 확인_닉네임 있는 유저_성공 상태 반환")
    @WithMockCustomUser(hasNickname = true)
    public void loginTest() throws Exception {

        //when
        ResultActions result = loginCheck();

        //then
        result.andExpect(status().isOk());
        result.andDo(UserTestDocs.documentOnLoginSuccess());
    }

    @Test
    @DisplayName("닉네임 추천받기_닉네임 있는 유저_성공 상태 반환")
    @WithMockCustomUser(hasNickname = true)
    public void nicknameRecommendTest() throws Exception {
        
        //get
        String email = "test@gmail.com";
        given(userService.RecommendNickname(email)).willReturn("test");
        
        //when
        ResultActions result = recommendNickname();

        //then
        result = verifyRecommendingNickname(result);
        result.andDo(UserTestDocs.documentOnNicknameRecommendation());
    }

    @Test
    @DisplayName("닉네임 중복 체크_닉네임_성공 반환")
    public void nicknameDuplicationCheckTest() throws Exception {

        //get
        String nickname = "test";

        //when
        ResultActions result = nicknameDuplicationCheck(nickname);

        //then
        result.andExpect(status().isOk());
        result.andDo(UserTestDocs.documentOnNicknameDuplicationCheck());
    }
    private ResultActions loginCheck() throws Exception {
        return mockMvc.perform(post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions recommendNickname() throws Exception {
        return mockMvc.perform(get("/user/nickname")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions nicknameDuplicationCheck(String nickname) throws Exception {
        String content = objectMapper.writeValueAsString(new CheckDuplicationRequest(nickname));

        return mockMvc.perform(post("/user/check-duplication")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
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

    private ResultActions verifyRecommendingNickname(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.nickname").value("test"));
    }


}


