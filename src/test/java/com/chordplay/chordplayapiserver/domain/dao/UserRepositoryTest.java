package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.MyVideo;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
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
class UserRepositoryTest {

    @Autowired private UserRepository userRepository;
    @Autowired private VideoRepository videoRepository;

    String testVideoId = "dinia_m0HGE";
    String testUserId = "6313b2381f8fa3bb122eaa78";

    @Test
    @DisplayName("user 정보의 collection에 비디오 id 추가하기_비디오")
    public void addVideoIdToCollectionTest() throws Exception {

        //get
        Video video = videoRepository.findById(testVideoId).orElseThrow(()-> new RuntimeException("비디오 없음"));
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
        
        //get
        Video video = videoRepository.findById(testVideoId).orElseThrow(()-> new RuntimeException("비디오 없음"));
        User prevUser = userRepository.findById(testUserId).orElseThrow(()-> new RuntimeException("유저 없음"));
        userRepository.addVideoIdToCollectionById(testUserId, video.getId());

        //when
        userRepository.deleteVideoIdToCollectionById(testUserId, video.getId());

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