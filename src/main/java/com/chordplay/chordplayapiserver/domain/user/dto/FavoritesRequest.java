package com.chordplay.chordplayapiserver.domain.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FavoritesRequest {
    private String country;
    private String performerGrade;
    private String gender;

    public FavoritesRequest(String country, String performerGrade, String gender) {
        this.country = country;
        this.performerGrade = performerGrade;
        this.gender = gender;
    }
}
