package com.chordplay.chordplayapiserver.domain.sheet.dto;

import lombok.Getter;

@Getter
public class AiStatusMessage {
    private String videoId;
    private int status;

    public AiStatusMessage(String videoId, int status) {
        this.videoId = videoId;
        this.status = status;
    }
}
