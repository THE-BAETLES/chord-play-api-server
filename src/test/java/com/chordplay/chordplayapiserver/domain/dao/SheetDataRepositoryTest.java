package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.Chord;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServer;
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
        int position = 200;
        Chord chord = new Chord("A","m7","C");
        sheetDataRepository.updateSheetChord(sheetData.getId(),position,chord);

        //then
        악보_코드_변경_검증하기(sheetData,position,chord);
        sheetDataRepository.deleteById(sheetData.getId());
    }


    private SheetData mockSheetData_생성하기(){
        Chord chord1 = new Chord("B","none","none");
        Chord chord2 = new Chord("none","none","none");
        Chord chord3 = new Chord("none","none","none");
        ChordInfo chordInfo1 = new ChordInfo(chord1, 0.1111);
        ChordInfo chordInfo2 = new ChordInfo(chord2, 0.1234);
        ChordInfo chordInfo3 = new ChordInfo(chord3, 0.3);
        return SheetData.builder()
                .id("6356acb55c54cb8bb51c90de")
                .bpm(123)
                .chordInfos(
                        Arrays.asList(chordInfo1,chordInfo2,chordInfo3)
                ).build();
    }
    private void 악보_코드_변경_검증하기(SheetData sheetData, int position, Chord chord ){
        SheetData foundSheetData = sheetDataRepository.findById(sheetData.getId()).get();
        assertThat(foundSheetData.getChordInfos().get(position).getChord().getRoot()).isEqualTo(chord.getRoot());
        assertThat(foundSheetData.getChordInfos().get(position).getChord().getTriad()).isEqualTo(chord.getTriad());
        assertThat(foundSheetData.getChordInfos().get(position).getChord().getBass()).isEqualTo(chord.getBass());
    }


}