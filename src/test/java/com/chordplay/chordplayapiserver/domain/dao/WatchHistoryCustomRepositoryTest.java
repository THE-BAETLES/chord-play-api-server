package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        Video video = videoRepository.findById("kdOS94IjzzE").get();

        watchHistoryRepository.updateCountAndTimeByUserAndVideo(user,video);
    }


    @Test
    @DisplayName("watch history 목록 가져오기")
    void get_watch_history(){
        List<WatchHistory> watchHistories = watchHistoryRepository.findByIdWithOffsetAndLimit("6303750118711564da868e80", 0,25);
        for(WatchHistory w : watchHistories){
            System.out.println(w.getLastPlayed());
        }
    }

}