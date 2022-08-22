package com.chordplay.chordplayapiserver.domain.recommendation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class PayloadResponse{
    private int number;
    private List<String> recommendationList;

    public PayloadResponse(int number, List<String> recommendationList) {
        this.number = number;
        this.recommendationList = recommendationList;
    }
}
