package com.chordplay.chordplayapiserver.domain.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service @Primary
public class SheetGenerateServiceImpl implements com.chordplay.chordplayapiserver.domain.sheet.service.SheetGenerateService {

    private final com.chordplay.chordplayapiserver.domain.sheet.service.ProgressService progressService;

    public SheetGenerateServiceImpl(com.chordplay.chordplayapiserver.domain.sheet.service.ProgressService progressService){
        this.progressService = progressService;
    }

    public void startLongPolling(){

    }

    @Override
    public void createSheet() {
        startLongPolling();
    }
}
