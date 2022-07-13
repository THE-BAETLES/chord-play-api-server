package com.chordplay.chordplayapiserver.domain.user;

import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TestUserRepositoryTest {

    @Autowired
    TestUserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("Test user")
    @Rollback(value = true)
    public void test1() {


        TestUser user = userRepository.findByUsername("12345");
        System.out.println(user);
        assertNull(user);

    }
}