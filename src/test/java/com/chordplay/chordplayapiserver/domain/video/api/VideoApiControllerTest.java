package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.user.config.SecurityConfig;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.util.WithMockCustomUser;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("컨트롤러 테스트")
@WebMvcTest(controllers = VideoApiController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@WithMockCustomUser
class VideoApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VideoService videoService;

    @Test
    @DisplayName("비디오 생성 후 가져오기")
    public void createVideoTest() throws Exception {

        //given
        Video video = createMockVideoData();
        given(videoService.create(video.getId())).willReturn(video);

        //when
        ResultActions result = createVideo(video);

        //then
        verifyCreatingVideo(result);
    }

    @Test
    @DisplayName("비디오 가져오기")
    public void getVideoTest() throws Exception{

        //given
        Video video = createMockVideoData();
        given(videoService.get(video.getId())).willReturn(video);

        //when
        ResultActions result = getVideo(video);

        //get
        verifyGettingVideo(result);
    }

    @Test
    @DisplayName("비디오 유튜브에서 검색하기")
    public void searchYoutubeVideoTest() throws Exception {

        //given
        String searchTitle = "장범준";
        List<VideoResponse> searchData = createMockSearchData();
        given(videoService.search(searchTitle)).willReturn(searchData);

        //when
        ResultActions result = searchYoutubeVideo(searchTitle);

        //then
        verifySearchingYoutubeVideo(result);
    }

    @Test
    @DisplayName("시청한 비디오 목록 가져오기")
    public void getWatchHistoryTest() throws Exception {

        //given
        List<VideoResponse> watchHistory = createMockWatchHistory();
        given(videoService.getWatchHistory(0,2)).willReturn(watchHistory);

        //when
        ResultActions result = getWatchHistory();

        //get
        verifyWatchHistory(result);
    }





    private List<VideoResponse> createMockSearchData(){
        return createMockVideDatas();
    }

    private List<VideoResponse> createMockWatchHistory(){
        return createMockVideDatas();
    }

    private Video createMockVideoData(){
        return Video.builder()
                .id("RFMmK9E-cbc")
                .thumbnailPath("https://i.ytimg.com/vi/RFMmK9E-cbc/hqdefault.jpg")
                .title("Go Back (고백)")
                .genre("")
                .createdAt(LocalDateTime.parse("2021-07-19T09:05:32"))
                .difficultyAvg(0)
                .length(210000)
                .playCount(0)
                .singer("Jang Beom June - Topic")
                .tags(Arrays.asList("JANG BEOM JUNE", "장범준", "Go Back", "첫 번째 '고백'", "고백"))
                .build();
    }

    private List<VideoResponse> createMockVideDatas(){

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
        videoResponses.add(videoResponse);

        return videoResponses;
    }

    private ResultActions createVideo(Video video) throws Exception {
        return mockMvc.perform(post("/videos/{videoId}", video.getId())
                        .contentType(MediaType.APPLICATION_JSON));
    }

    private ResultActions getVideo(Video video) throws Exception {
        return mockMvc.perform(get("/videos/{videoId}", video.getId())
                        .contentType(MediaType.APPLICATION_JSON));
    }

    private ResultActions searchYoutubeVideo(String searchTitle) throws Exception {
        return mockMvc.perform(get("/videos/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("searchTitle", "장범준"));

    }

    private ResultActions getWatchHistory() throws Exception {
        return mockMvc.perform(get("/videos/watch-history")
                .contentType(MediaType.APPLICATION_JSON)
                .param("offset", "0")
                .param("limit", "2"));
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

    private void verifyCreatingVideo(ResultActions result) throws Exception {
        verifyCreated(result);
        result.andExpect(jsonPath("$.data.title").value("Go Back (고백)"));
    }

    private void  verifyGettingVideo(ResultActions result) throws Exception {
        verifyOK(result);
        result.andExpect(jsonPath("$.data.title").value("Go Back (고백)"));
    }

    private void verifySearchingYoutubeVideo(ResultActions result) throws Exception {
        verifyOK(result);
        result.andExpect(jsonPath("$.data.length()").value(2));
    }

    private void verifyWatchHistory(ResultActions result) throws Exception {
        verifyOK(result);
        result.andExpect(jsonPath("$.data.length()").value(2));
    }





}