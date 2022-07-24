package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApiControllerTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    @DisplayName("Test user")
    @Rollback(value = false)
    public void test(){
        System.out.println(userService.RecommendNickname("pove2019@gmail.com"));
    }
}