package com.chordplay.chordplayapiserver.domain.sheet.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProgressServiceImpl implements ProgressService {

    @Override
    public int checkStatus(String videoId) {
        return 0;
    }
}
