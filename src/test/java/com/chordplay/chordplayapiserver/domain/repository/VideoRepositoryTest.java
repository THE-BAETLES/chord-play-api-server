package com.chordplay.chordplayapiserver.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired VideoRepository videoRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testVideo(){

    }

}