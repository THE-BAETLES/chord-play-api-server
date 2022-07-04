package com.chordplay.chordplayapiserver.domain.sheet.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service @Primary
public class SheetGenerateServiceImpl implements SheetGenerateService{

    private final ProgressService progressService;

    public SheetGenerateServiceImpl(ProgressService progressService){
        this.progressService = progressService;
    }

    public void startLongPolling(){

    }

    @Override
    public void createSheet() {
        startLongPolling();
    }
}
