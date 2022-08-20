package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.dao.VideoRepository;
import com.chordplay.chordplayapiserver.domain.dao.WatchHistoryRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SheetServiceImplTest {

    @Autowired
    SheetRepository sheetRepository;
    @Autowired
    SheetService sheetService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    WatchHistoryRepository watchHistoryRepository;
    @Test
    void test1(){
        Sheet sheet = Sheet.builder()
                .user(userRepository.findByUsername("root")).video(videoRepository.findById("pnaQ9CbE6P0").get()).title("hi").build();
        sheetRepository.save(sheet);
    }

    @Test
    void test2(){

        String userId = "62ff43ac801877d826eb0c7d";
        String videoId = "29ycT6fA-Rs";
        updateWatchHistory(userId, videoId);
    }

    @Test
    void test3(){
        String userId = "62ff43ac801877d826eb0c7d";
        List<WatchHistory> watchHistories = watchHistoryRepository.findByIdWithOffsetAndLimit(userId, 0, 5);
        for ( WatchHistory w : watchHistories) {
            System.out.println(w);
        }
    }
    @Test
    @DisplayName("공유된 악보 불러오기")
    void getSharedSheets(){
        List<Sheet> sheets = sheetService.getSharedSheets("29ycT6fA-Rs");
        for(Sheet s : sheets){
            System.out.println(s.getTitle());
        }
    }


    private void updateWatchHistory(String userId, String videoId){
        Video video = new com.chordplay.chordplayapiserver.domain.entity.Video(videoId);
        //watchHistoryRepository.updateCountAndTimeByUserAndVideo(user, video);
        watchHistoryRepository.updateCountAndTimeByUserAndVideo(userId, videoId);
    }


}