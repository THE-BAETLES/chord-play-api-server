package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Video Repository 테스트")
class VideoRepositoryTest extends AcceptanceTest {


    @Autowired
    VideoRepository videoRepository;

    @Autowired
    WatchHistoryRepository watchHistoryRepository;


    @Test
    @DisplayName("play count 조회하기_성공")
    public void getPlayCount() throws Exception {

        //get
        User user = new User(getTestUserId());
        User user2 =new User("test");
        Video video = new Video(getTestVideoId());
        WatchHistory watchHistory = WatchHistory.builder()
                .user(user)
                .video(video)
                .playCount(3L)
                .build();

        WatchHistory watchHistory2 = WatchHistory.builder()
                .user(user2)
                .video(video)
                .playCount(2L)
                .build();
        videoRepository.save(video);
        watchHistoryRepository.save(watchHistory);
        watchHistoryRepository.save(watchHistory2);

        //when
        Long playCount = videoRepository.getPlayCount(video.getId());

        //then
        assertThat(playCount).isEqualTo(5);
    }

    @Test
    @DisplayName("play count 조회하기_없는 경우_0 반환")
    public void getPlayCount_empty() throws Exception {

        //get
        User user = new User(getTestUserId());
        Video video = new Video(getTestVideoId());

        videoRepository.save(video);

        //when
        Long playCount = videoRepository.getPlayCount(video.getId());

        //then
        assertThat(playCount).isEqualTo(0L);
    }



}