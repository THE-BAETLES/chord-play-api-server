package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatchHistoryCustomRepositoryTest {


    @Autowired
    WatchHistoryRepository watchHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;
    @Test
    void watch_history_생성_수정(){
        User user = userRepository.findByUsername("최현준");
        Video video = videoRepository.findById("BUzI-awsi1s").get();

        watchHistoryRepository.updateCountAndTimeByUserAndVideo(user,video);
    }

//    @Test
//    @DisplayName("watch history 목록 가져오기")
//    void

}