package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetsResponse;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheetLike.service.SheetLikeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Sheet Repository 테스트")
class SheetRepositoryTest extends AcceptanceTest {

    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private SheetLikeRepository sheetLikeRepository;



    @Test
    @DisplayName("user와 videoId에 관련된 sheets 찾기")
    public void findAllByUserAndVideoIdTest() throws Exception {
        
        //get
        Video video = Video.builder().id(getTestVideoId()).build();
        User user = new User(getTestUserId());

        Sheet sheet = Sheet.builder()
                .user(user)
                .video(video).build();
        Sheet savedSheet = sheetRepository.save(sheet);
        SheetLike sheetLike = new SheetLike(user,sheet, LocalDateTime.now());
        sheetLikeRepository.save(sheetLike);

        //when

        List<Sheet> sheets = sheetRepository.findAllByUserAndVideoId(user, video.getId());

        //then
        assertThat(sheets.size()).isEqualTo(1);
        assertThat(sheets.get(0).getId()).isEqualTo(savedSheet.getId());

    }

    @Test
    @DisplayName("sheet-like count 가져오기_sheetLike 1개 저장_1개 반환 성공")
    public void getSheetLikeCount() throws Exception {

        //get
        Video video = Video.builder().id(getTestVideoId()).build();
        User user = new User(getTestUserId());

        Sheet sheet = Sheet.builder()
                .user(user)
                .video(video).build();
        Sheet savedSheet = sheetRepository.save(sheet);
        SheetLike sheetLike = new SheetLike(user,sheet,LocalDateTime.now());
        sheetLikeRepository.save(sheetLike);

        //when
        Long sheetLikeCount = sheetRepository.getSheetLikeCount(sheet.getId());

        //then
        assertThat(sheetLikeCount).isEqualTo(1);

    }
}