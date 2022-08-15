package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired VideoRepository videoRepository;

    @Test
    public void 비디오의_악보_개수_가져오기_성공(){

        Long sheetCount = videoRepository.getSheetCount("pnaQ9CbE6P0");
        Assertions.assertEquals(2, sheetCount);
    }
    @Test
    public void 비디오의_악보_개수_가져오기_실패(){

        Long sheetCount = videoRepository.getSheetCount("anything");
        Assertions.assertEquals(1, sheetCount);
    }

}