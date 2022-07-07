package com.chordplay.chordplayapiserver.service;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.repository.SheetDataRepository;
import com.chordplay.dto.SheetDataRequestDTO;
import com.chordplay.dto.SheetDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;

    public SheetDataResponseDTO read(String id){

        SheetData sheetData = sheetDataRepository.findById(id).get();
        return new SheetDataResponseDTO(sheetData);
    }

}