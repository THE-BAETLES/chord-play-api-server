package com.chordplay.chordplayapiserver.domain.video.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class YoutubeVideoSearchTest {
    @Autowired
    YoutubeVideoSearch youtubeVideoSearch;

    @Test
    void youtube_search_test(){

        youtubeVideoSearch.search_KR("크러쉬");
    }
}