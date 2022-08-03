package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AiStatusMessage {
    private String videoId;
    private int status;

    public AiStatusMessage(String videoId, int status) {
        this.videoId = videoId;
        this.status = status;
    }
    public AiStatusMessage(){
    }
}
