package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("Test nickname-duplication")
    @Rollback(value = true)
    public void test1(){

        User result3 = userRepository.findByUsername("1234");
        System.out.println(result3);
        Boolean result = userRepository.existsByNickname("qwer");
        System.out.println(result);

    }

}