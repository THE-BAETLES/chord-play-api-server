package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.dao.VideoRepository;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VideoServiceTest {

    @Autowired VideoService videoService;
    @Autowired
    VideoRepository videoRepository;

    @Test
    void 검색(){
        List<VideoResponse> videoResponseList = videoService.search("장범준");
        for (VideoResponse videoResponse : videoResponseList){
            System.out.println(videoResponse);
        }
    }

    @Test
    void 비디오_생성(){

        videoService.create("TsopAwOqsgc");
        Video video = videoRepository.findById("TsopAwOqsgc").get();
        System.out.println(video);

    }

}