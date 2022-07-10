package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service @Primary
@RequiredArgsConstructor
public class SheetGenerateServiceImpl implements SheetGenerateService {

    private final SheetDataRepository sheetDataRepository;
    private final SheetRepository sheetRepository;
    private final SendMessageRequest sendMessageRequest;
    @Override @Transactional
    public void createAi(SheetAiRequest dto) {

        Sheet sheet = dto.toEntity(new User("testId"));  // JWT 구현 후 변경
        sheetRepository.save(sheet);


        // 일단 끝 만 해보고

        // redis cache 체크

        //redis sub 등록

        // redis 체크 (값이 다르다? => 반환)

        // 이벤트 오면 반환..? 아마?

    }
}
