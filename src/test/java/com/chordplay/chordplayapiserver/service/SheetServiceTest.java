package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SheetServiceTest {
    @Autowired
    SheetDataRepository sheetDataRepository;

    @Test
    @Transactional
    @DisplayName("Sheet Data read test 생성, 조회")
    @Rollback(value = true)
    public void test1(){
        //생성
        SheetData sd1 = SheetData.builder().id("testId").build();
        sheetDataRepository.save(sd1);
        //조회
        SheetData sd2 = sheetDataRepository.findById("testId").get();
        System.out.println(sd1);
        System.out.println(sd2);
    }
}