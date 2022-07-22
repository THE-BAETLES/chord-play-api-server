package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class UserServiceTest {
//    @Autowired
//    UserService userService;
//    JoinRequest joinRequest = JoinRequest.builder().email("test@gmail.com").username("chanwoo").firebaseUid("test").build();
//
//    @Test
//    @Transactional
//    @DisplayName("Join 성공")
//    @Rollback(value = true)
//    public void test1() {
//        userService.join(joinRequest);
//
//    }
}