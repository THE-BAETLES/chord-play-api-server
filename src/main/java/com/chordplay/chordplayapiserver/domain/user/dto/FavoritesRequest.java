package com.chordplay.chordplayapiserver.domain.user.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FavoritesRequest {
    private String country;
    private String performer_grade;
    private String gender;

    public FavoritesRequest(String country, String performer_grade, String gender) {
        this.country = country;
        this.performer_grade = performer_grade;
        this.gender = gender;
    }
}
