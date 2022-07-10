package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class SheetGenerateServiceImplTest {

    @Autowired SheetRepository sheetRepository;
    @Autowired UserRepository ur;

    @Test
    @DisplayName("Mongo Reference Test")
    @Rollback(value = false)
    public void test(){

        Sheet sheet = sheetRepository.findById("62c977578bc732683a6f5b3a").get();
        System.out.println(sheet.getVideo().getTitle( ));

    }
}