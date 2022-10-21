package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("SheetData Repository 테스트")
class SheetDataRepositoryTest {

    @Autowired
    private SheetDataRepository sheetDataRepository;

    @Test
    @DisplayName("악보 데이터 내의 특정 코드 수정하기")
    void updateSheetChordTest(){

        //given
        SheetData sheetData = mockSheetData_생성하기();
        sheetDataRepository.save(sheetData);

        //when
        sheetDataRepository.updateSheetChord(sheetData.getId(),3, "B");

        //then
        악보_코드_변경_검증하기(sheetData);
        sheetDataRepository.deleteById(sheetData.getId());
    }


    private SheetData mockSheetData_생성하기(){
        return SheetData.builder()
                .id("abc")
                .bpm(123)
                .chordInfos(
                        Arrays.asList(ChordInfo.builder()
                                .position(3)
                                .chord("A").build()
                        )
                ).build();
    }
    private void 악보_코드_변경_검증하기(SheetData sheetData){
        SheetData foundSheetData = sheetDataRepository.findById(sheetData.getId()).get();
        assertThat(foundSheetData.getChordInfos().get(0).getChord()).isEqualTo("B");
    }


}