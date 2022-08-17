package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SheetApiControllerTest {

    @Autowired
    SheetService sheetService;

    @Test
    @Rollback
    @DisplayName("악보, 악보데이터 삭제")
    void deleteSheetAndSheetData(){
        Sheet sheet = sheetService.deleteSheetAndSheetData("62f351d6e4587f02387ca0a1");
        Sheet sheet1 = sheetService.getSheet(sheet.getId());
        System.out.println(sheet1.getId());
    }
}