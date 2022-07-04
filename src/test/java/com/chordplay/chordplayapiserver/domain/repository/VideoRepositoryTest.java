package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired VideoRepository videoRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testVideo(){

        Video video = new Video();
        video.setId("mongo");
        video.setThumnail_path("path");
        video.setVid_youtube("youtube");
        video.setTitle("title");
        video.setGenre("genre");
        video.setSinger("singer");
        video.setDifficulty_avg(3);
        video.setPlay_count(3);

        videoRepository.save(video);

        Video testVideo = videoRepository.findById(video.getId()).get();
        assertEquals(testVideo.getId(), video.getId());

    }

}