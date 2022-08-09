package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired VideoRepository videoRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testVideo(){



        Video video = Video.builder()
                .id("pnaQ9CbE6P0")
                .thumbnailPath("https://img.youtube.com/vi/pnaQ9CbE6P0/0.jpg")
                .title("자우림 '스물다섯, 스물하나' 어쿠스틱커버 by 장범준 Acoustic COVER")
                .genre("performance")
                .singer("장범준")
                .difficultyAvg(4)
                .playCount(188574)
                .build();
        videoRepository.save(video);

    }

}