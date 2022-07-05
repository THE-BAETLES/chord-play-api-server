package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.MyVideo;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepoTest {


    @Autowired UserRepository userRepository;
    @Autowired VideoRepository videoRepository;
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testV(){
//
////        Video video = Video.builder()
////                .id("zzzzz").vid_youtube("vid").title("title").genre("genre").singer("singer")
////                .build();
////
////        videoRepository.save(video)
//
//
//        Video testVideo = videoRepository.findById("zzzzz").get();
//
//        MyVideo vid1 = MyVideo.builder().video(testVideo).build();
//        List<MyVideo> myVideos = new ArrayList<MyVideo>();
//        myVideos.add(vid1);
//
//        User user = User.builder().id("test").social_id("user").myCollection(myVideos).build();
//
//        userRepository.save(user);
//
//        //assertEquals(testVideo.getId(), video.getId());
//
//    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void defaultUserTest(){

        Video video = videoRepository.findById("zzzzz").get();
        MyVideo myVideo = MyVideo.builder().video(video).last_played().build();
        List<MyVideo> myCollection = new ArrayList<MyVideo>();
        myCollection.add(myVideo);
        User user = User.builder().myCollection(myCollection).build();

        userRepository.save(user);


        //assertEquals(testVideo.getId(), video.getId());

    }
}
