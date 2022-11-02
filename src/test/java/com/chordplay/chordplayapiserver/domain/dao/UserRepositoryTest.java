package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.MyVideo;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DisplayName("User Repository 테스트")
class UserRepositoryTest extends AcceptanceTest {

    @Autowired private UserRepository userRepository;
    @Autowired private VideoService videoService;


    @Test
    @DisplayName("user 정보의 collection에 비디오 id 추가하기_비디오")
    public void addVideoIdToCollectionTest() throws Exception {
        String testVideoId = getTestVideoId();
        String testUserId = getTestUserId();
        //get
        Video video = videoService.create(testVideoId);
        User prevUser = userRepository.findById(testUserId).orElseThrow(()-> new RuntimeException("유저 없음"));

        //when
        userRepository.addVideoIdToCollectionById(testUserId, video.getId());

        //then
        User user = userRepository.findById(testUserId).orElseThrow(()-> new RuntimeException("유저 없음"));
        List<String> myCollection = user.getMyCollection();

        Boolean same = false;

        for(String videoId : myCollection){
            if (videoId.equals(testVideoId)) {
                same = true;
            }
        }

        assertThat(same).isTrue();

        //rollback
        userRepository.delete(user);
        userRepository.save(prevUser);
    }
    
    @Test
    @DisplayName("user 정보의 collection에 비디오 id 삭제하기_비디오Id_성공반환")
    public void deleteVideoIdToCollectionTest() throws Exception {
        String testVideoId = getTestVideoId();
        String testUserId = getTestUserId();

        //get
        Video video = videoService.create(testVideoId);
        User prevUser = userRepository.findById(testUserId).orElseThrow(()-> new RuntimeException("유저 없음"));
        userRepository.addVideoIdToCollectionById(testUserId, video.getId());

        //when
        userRepository.deleteVideoIdFromCollectionById(testUserId, video.getId());

        //then
        User user = userRepository.findById(testUserId).orElseThrow(()-> new RuntimeException("유저 없음"));
        List<String> myCollection = user.getMyCollection();

        Boolean same = false;

        for(String videoId : myCollection){
            if (videoId.equals(testVideoId)) {
                same = true;
            }
        }

        assertThat(same).isFalse();

        //rollback
        userRepository.delete(user);
        userRepository.save(prevUser);
    
    }








}