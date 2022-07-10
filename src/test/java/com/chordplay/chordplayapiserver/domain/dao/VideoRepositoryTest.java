package com.chordplay.chordplayapiserver.domain.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired VideoRepository videoRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testVideo(){

    }

}