package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetChangeRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDuplicationRequest;
import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetNotFoundException;
import com.chordplay.chordplayapiserver.global.ServiceUnitTest;
import com.chordplay.chordplayapiserver.global.exception.ForbiddenException;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Sheet 서비스 테스트")

class SheetServiceImplTest extends ServiceUnitTest {

    @InjectMocks
    SheetServiceImpl sheetService;
    @Mock
    SheetRepository sheetRepository;

    @Mock
    SheetDataRepository sheetDataRepository;



    @Test
    @DisplayName("악보 코드 변경하기_없는 악보 호출_오류 반환")
    public void updateSheetChordSheetExceptionTest() throws Exception {

        //get
        Sheet sheet = createMockSheet();
        given(sheetRepository.findById(sheet.getId())).willReturn(Optional.empty());

        //when
        assertThatThrownBy(() -> { sheetService.updateSheetChord(sheet.getId(), new SheetChangeRequest(0,"Bm")); })
                .isInstanceOf(SheetNotFoundException.class);
        //then
    }

    @Test
    @DisplayName("악보 코드 변경하기_다른 유저의 악보_오류 반환")
    public void updateSheetChordUserExceptionTest() throws Exception {

        //get
        Sheet sheet = createMockSheetOfOtherUser();
        given(sheetRepository.findById(sheet.getId())).willReturn(Optional.of(sheet));


        //when
        assertThatThrownBy(() -> { sheetService.updateSheetChord(sheet.getId(), new SheetChangeRequest(0,"Bm")); })
                .isInstanceOf(ForbiddenException.class);
        //then
    }

    @Test
    @DisplayName("악보 코드 변경하기_악보 주인 성공 반환")
    public void updateSheetChordSuccessTest() throws Exception {

        //get
        Sheet sheet = createMockSheet();
        given(sheetRepository.findById(sheet.getId())).willReturn(Optional.of(sheet));

        //when
        assertThatCode(() -> { sheetService.updateSheetChord(sheet.getId(), new SheetChangeRequest(0,"Bm")); }).doesNotThrowAnyException();
        //then
    
    }

    @Test
    @DisplayName("악보 복제하기_정상 입력_성공 반환")
    public void duplicateSheetSuccessTest() throws Exception {

        //get
        Sheet sheet = createMockSheet();
        SheetData sheetData = createMockSheetData();
        User user = createMockUser();
        SheetDuplicationRequest dto = new SheetDuplicationRequest(sheet.getId(),"The Song - new version");
        Sheet newSheet = createMockNewSheet(sheet ,user, dto.getTitle());

        Sheet newSheetWithId = createMockNewSheetWithId(newSheet);
        given(sheetRepository.findById(sheet.getId())).willReturn(Optional.of(sheet));
        given(sheetDataRepository.findById(sheet.getId())).willReturn(Optional.of(sheetData));
        given(sheetRepository.save(any(Sheet.class))).willReturn(newSheetWithId);

        //when
        Sheet returnedSheet = sheetService.duplicateSheet(dto);

        //then
        assertThat(returnedSheet.getTitle()).isEqualTo(newSheet.getTitle());
        assertThat(returnedSheet.getUser().getId()).isEqualTo(newSheet.getUser().getId());
    }

    private Sheet createMockSheet(){

        User user = createMockUser();
        return Sheet.builder()
                .updatedAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .video(new Video("KZH-MpiwmaU"))
                .user(user)
                .title("Chord Play")
                .createdAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .id("6300d7e8aeeb0778c43ea37d")
                .build();
    }
    private Sheet createMockSheetOfOtherUser(){

        User user = new User("otherUser");
        return Sheet.builder()
                .updatedAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .video(new Video("KZH-MpiwmaU"))
                .user(user)
                .title("Chord Play")
                .createdAt(ZonedDateTime.parse("2022-08-20T12:47:36.426+00:00").toLocalDateTime())
                .id("6300d7e8aeeb0778c43ea37d")
                .build();
    }

    private Sheet createMockNewSheet(Sheet sheet,User user, String title){
        return Sheet.builder()
                .video(sheet.getVideo())
                .user(user)
                .title(title)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private Sheet createMockNewSheetWithId(Sheet sheet){
        return Sheet.builder()
                .id("newID")
                .video(sheet.getVideo())
                .user(sheet.getUser())
                .title(sheet.getTitle())
                .createdAt(sheet.getCreatedAt())
                .updatedAt(sheet.getUpdatedAt())
                .build();
    }

    private SheetData createMockSheetData() {
        List<ChordInfo> chordInfos = new ArrayList<>();

        chordInfos.add(ChordInfo.builder()
                .chord("Am")
                .start(0.0)
                .end(2.23)
                .position(0).build());

        chordInfos.add(ChordInfo.builder()
                .chord("C")
                .start(2.23)
                .end(4.06)
                .position(1).build());
        return SheetData.builder()
                .bpm(123)
                .id("6300d7e8aeeb0778c43ea37d")
                .chordInfos(chordInfos).build();
    }

    private static User createStaticMockUser(){
        return User.builder()
                .id("6313b2381f8fa3bb122eaa78")
                .username("최현준")
                .email("test@gmail.com")
                .nickname("test")
                .roles("ROLE_USER")
                .build();
    }

    private User createMockUser(){
        return User.builder()
                .id("6313b2381f8fa3bb122eaa78")
                .username("최현준")
                .email("test@gmail.com")
                .nickname("test")
                .roles("ROLE_USER")
                .build();
    }
}