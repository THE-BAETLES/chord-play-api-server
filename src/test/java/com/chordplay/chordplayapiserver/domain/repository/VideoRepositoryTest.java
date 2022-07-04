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
    public void testV(){

        Video video = Video.builder()
                .id("zzzzz").vid_youtube("vid").title("title").genre("genre").singer("singer")
                        .build();

        videoRepository.save(video);

        Video testVideo = videoRepository.findById(video.getId()).get();
        System.out.println(testVideo.getId() + " = " + video.getId());
        assertEquals(testVideo.getId(), video.getId());

    }

}