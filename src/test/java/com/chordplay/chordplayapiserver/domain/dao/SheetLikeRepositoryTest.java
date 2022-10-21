package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sheet-Like Repository 테스트")
class SheetLikeRepositoryTest extends AcceptanceTest {

    @Autowired
    private SheetLikeRepository sheetLikeRepository;

    @Test
    @DisplayName("특정 유저가 좋아요한 악보 삭제_sheetId_성공")
    public void deleteSheetLikeTest() throws Exception {
        //get
        User user = new User("test");

        Sheet sheet = Sheet.builder().id("test").build();
        SheetLike sheetLike = saveSheetLike(user, sheet);

        //when
        Optional<SheetLike> deletedSheetLikeOptional = sheetLikeRepository.deleteBySheetAndUser(sheet, user);

        //then
        Optional<SheetLike> sheetLikeOptional = sheetLikeRepository.findById(sheetLike.getId());
        assertThat(sheetLikeOptional.isEmpty()).isTrue();
    
    }

    @Test
    @DisplayName("특정 유저의 악보 좋아요 찾기__성공")
    public void findBySheetAndUserTest() throws Exception {
        //get
        User user = new User("test");

        Sheet sheet = Sheet.builder().id("test").build();
        SheetLike sheetLike = saveSheetLike(user, sheet);

        //when
        Optional<SheetLike> sheetLikeOptional = sheetLikeRepository.findBySheetAndUser(sheet, user);

        //then
        assertThat(sheetLikeOptional.get().getId()).isEqualTo(sheetLike.getId());

    }

    private SheetLike saveSheetLike(User user, Sheet sheet){
        SheetLike sheetLike = new SheetLike(user, sheet);
        return sheetLikeRepository.save(sheetLike);
    }

}