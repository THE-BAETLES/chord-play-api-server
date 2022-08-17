package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VideoApiControllerTest {

    @Autowired
    SheetService sheetService;

    @Test
    @DisplayName("악보 가져오기")
    void getSheet(){
        Sheet sheet = sheetService.getSheet("62f351d6e4587f02387ca0a1");
        Assertions.assertEquals(sheet.getId(), "62f351d6e4587f02387ca0a1");
    }


}