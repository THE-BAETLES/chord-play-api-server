package com.chordplay.chordplayapiserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service @Primary
@RequiredArgsConstructor
public class SheetGenerateServiceImpl implements SheetGenerateService {

    @Override @Transactional
    public void aiCreate() {

    }
}
