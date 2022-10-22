package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.item.Country;
import com.chordplay.chordplayapiserver.domain.entity.item.Gender;
import com.chordplay.chordplayapiserver.domain.entity.item.PerformerGrade;
import com.chordplay.chordplayapiserver.domain.sheet.api.SheetApiController;
import com.chordplay.chordplayapiserver.domain.sheet.docs.SheetTestDocs;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.user.docs.UserTestDocs;
import com.chordplay.chordplayapiserver.domain.user.dto.CheckDuplicationRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.NicknameResponse;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.util.WithMockCustomUser;
import com.chordplay.chordplayapiserver.util.WithMockCustomUserSecurityContextFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.sql.Array;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
    @DisplayName("내 정보 반환 요청_닉네임 있는 유저_내 정보 반환")
    @WithMockCustomUser(hasNickname = true)
    public void getMyInformationTest() throws Exception {

        //get
        User user = WithMockCustomUserSecurityContextFactory.getAdminUser(true);

        //when
        ResultActions result = getMyInformation();

        //then
        verifyOK(result);
        result.andExpect(jsonPath("$.data.nickname").value(user.getNickname()))
                .andExpect(jsonPath("$.data.id").value(user.getId()))
                .andExpect(jsonPath("$.data.roles").value(user.getRoles()));
        result.andDo(UserTestDocs.documentOnMyInformation());
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

    @Test
    @DisplayName("회원가입_회원가입 정보_회원가입 성공 반환")
    @WithMockCustomUser
    public void joinTest() throws Exception {

        //get
        JoinRequest requestBody = CreateMockJoinRequestBody();

        //when
        ResultActions result = join(requestBody);

        //then
        result.andExpect(status().isCreated());
        result.andDo(UserTestDocs.documentOnJoining());
    }

    @Test
    @DisplayName("my collection에 videoId 추가하기_video id_성공 반환(201)")
    @WithMockCustomUser
    public void addVideoIdToMyCollectionTest() throws Exception {

        //get
        String videoId = "video_id";

        //when
        ResultActions result = addVideoIdToMyCollection(videoId);

        //then
        verifyCreated(result);
        result.andDo(UserTestDocs.documentOnAddingVideoIdToMyCollection());
    }

    @Test
    @DisplayName("my collection에 videoId 삭제하기_video id_성공 반환(200)")
    @WithMockCustomUser
    public void deleteVideoIdToMyCollectionTest() throws Exception {

        //get
        String videoId = "video_id";

        //when
        ResultActions result = deleteVideoIdFromMyCollection(videoId);

        //then
        verifyOK(result);
        result.andDo(UserTestDocs.documentOnDeletingVideoIdFromMyCollection());
    }

    @Test
    @DisplayName("my collection 가져오기_ _성공 반환(200)")
    @WithMockCustomUser
    public void getMyCollectionTest() throws Exception {

        //get
        List<VideoResponse> videoResponses = createMockVideoResponses();
        given(userService.getMyCollection()).willReturn(videoResponses);
        //when
        ResultActions result = getMyCollection();

        //then
        verifyGettingMyCollection(result);
        result.andDo(UserTestDocs.documentOnGettingMyCollection());
    }

    @Test
    @DisplayName("my collection의 video Id list 가져오기_ _성공 반환(200)")
    @WithMockCustomUser
    public void getMyCollectionVideoIdListTest() throws Exception {

        //get
        List<String> myCollectionVideoIdList= Arrays.asList("videoId1","videoId2","videoId3");
        given(userService.getMyCollectionVideoIdList()).willReturn(myCollectionVideoIdList);

        //when
        ResultActions result = getMyCollectionVideoIdList();

        //then
        verifyGettingMyCollectionVideoIdList(result);
        result.andDo(UserTestDocs.documentOnGettingMyCollectionVideoIdList());
    }

    private JoinRequest CreateMockJoinRequestBody() {
        return JoinRequest.builder()
                .country(Country.KR)
                .performerGrade(PerformerGrade.BEGINNER)
                .nickname("test")
                .gender(Gender.MALE)
                .signupFavorite(Arrays.asList("videoId1", "videoId2"))
                .build();
    }

    private List<VideoResponse> createMockVideoResponses(){

        List<VideoResponse> videoResponses = new ArrayList<>();
        VideoResponse videoResponse = VideoResponse.builder()
                .sheetCount(0L)
                .tags(new ArrayList<>())
                .singer("불면증")
                .playCount(0)
                .length(0)
                .difficultyAvg(0)
                .genre("")
                .title("장범준(with버스커버스커) 명곡 모음")
                .thumbnailPath("https://i.ytimg.com/vi/dinia_m0HGE/hqdefault.jpg")
                .id("dinia_m0HGE")
                .createdAt("2020-09-27T20:19:09")
                .build();
        videoResponses.add(videoResponse);
        return videoResponses;
    }

    private ResultActions loginCheck() throws Exception {
        return mockMvc.perform(post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions getMyInformation() throws Exception {
        return mockMvc.perform(get("/user")
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

    private ResultActions join(JoinRequest requestBody) throws Exception {
        String content = objectMapper.writeValueAsString(requestBody);

        return mockMvc.perform(post("/user/join")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions addVideoIdToMyCollection(String videoId) throws Exception{
        return mockMvc.perform(post("/user/my-collection/{videoId}",videoId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions deleteVideoIdFromMyCollection(String videoId) throws Exception{
        return mockMvc.perform(delete("/user/my-collection/{videoId}",videoId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }

    private ResultActions getMyCollection() throws Exception {
        return mockMvc.perform(get("/user/my-collection")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer {token}"));
    }
    private ResultActions getMyCollectionVideoIdList() throws Exception {
        return mockMvc.perform(get("/user/my-collection-video-id-list")
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

    private ResultActions verifyGettingMyCollection(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.length()").value(1));
    }

    private ResultActions verifyGettingMyCollectionVideoIdList(ResultActions result) throws Exception {
        verifyOK(result);
        return result.andExpect(jsonPath("$.data.length()").value(3));
    }

}


