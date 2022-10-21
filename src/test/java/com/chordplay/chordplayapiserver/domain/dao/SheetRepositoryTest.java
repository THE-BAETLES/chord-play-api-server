package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.acceptance.global.AcceptanceTest;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Sheet Repository 테스트")
class SheetRepositoryTest extends AcceptanceTest {

    @Autowired
    private SheetRepository sheetRepository;



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

        //when

        List<Sheet> sheets = sheetRepository.findAllByUserAndVideoId(user, video.getId());

        //then
        assertThat(sheets.size()).isEqualTo(1);
        assertThat(sheets.get(0).getId()).isEqualTo(savedSheet.getId());

    }
}