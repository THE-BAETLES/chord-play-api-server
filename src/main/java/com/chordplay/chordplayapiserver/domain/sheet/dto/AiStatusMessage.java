package com.chordplay.chordplayapiserver.domain.sheet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AiStatusMessage {
    private String status;

    public AiStatusMessage(String status) {
        this.status = status;
    }
    public AiStatusMessage(){
    }
}
