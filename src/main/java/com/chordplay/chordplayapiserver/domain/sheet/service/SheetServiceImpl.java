package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dao.EmitterRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.dao.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;



    @Override
    public void createAi(SheetAiRequest dto) {
        
    }

    public SheetDataResponse read(String id){

        SheetData sheetData = sheetDataRepository.findById(id).get();
        return new SheetDataResponse(sheetData);
    }





}
