package com.chordplay.chordplayapiserver.domain.video.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceTest {


    @Autowired
    YoutubeVideoSearch searchService;

    @Test
    void video_search_test(){
        searchService.search_KR("버스커버스커");


    }

}