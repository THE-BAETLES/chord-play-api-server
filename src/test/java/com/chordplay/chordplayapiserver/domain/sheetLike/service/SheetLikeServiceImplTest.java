package com.chordplay.chordplayapiserver.domain.sheetLike.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetLikeRepository;
import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetChangeRequest;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheetLike.exception.SheetLikeNotFoundException;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.chordplay.chordplayapiserver.global.ServiceUnitTest;
import com.chordplay.chordplayapiserver.global.exception.ForbiddenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Provider;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@DisplayName("Sheet-Like 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class SheetLikeServiceImplTest extends ServiceUnitTest {

    @InjectMocks
    private SheetLikeServiceImpl sheetLikeService;
    @Mock
    private SheetLikeRepository sheetLikeRepository;
    @Mock
    private SheetService sheetService;
    @Mock
    private UserService userService;


    @Test
    @DisplayName("악보좋아요 삭제_좋아요를 안누른 악보ID_성공 반환")
    public void deleteSheetLikeSuccess() {

        //get
        String badSheetId = "badSheetId";
        Sheet sheet = Sheet.builder().build();
        User user = new User("test");
        given(sheetService.getSheet(badSheetId)).willReturn(sheet);
        given(userService.getUser(any(String.class))).willReturn(user);
        given(sheetLikeRepository.deleteBySheetAndUser(any(Sheet.class), any(User.class))).willReturn(Optional.of(new SheetLike(user, sheet)));

        //when
        assertThatCode(() -> { sheetLikeService.deleteLike(badSheetId);}).doesNotThrowAnyException();

        //then

    }

    @Test
    @DisplayName("악보좋아요 삭제_좋아요를 안누른 악보ID_exception반환")
    public void deleteSheetLikeFail() {

        //get
        String badSheetId = "badSheetId";
        given(sheetService.getSheet(badSheetId)).willReturn(Sheet.builder().build());
        given(userService.getUser(any(String.class))).willReturn(new User("test"));
        given(sheetLikeRepository.deleteBySheetAndUser(any(Sheet.class), any(User.class))).willReturn(Optional.empty());

        //when

        assertThatThrownBy(() -> { sheetLikeService.deleteLike(badSheetId);})
                .isInstanceOf(SheetLikeNotFoundException.class);
        //then
    
    }







}