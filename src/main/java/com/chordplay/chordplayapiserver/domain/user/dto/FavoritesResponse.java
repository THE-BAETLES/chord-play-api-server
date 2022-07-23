package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FavoritesResponse {
    private List<Video> favorites;

    @Builder
    public FavoritesResponse(List<Video> favorites) {
        this.favorites = favorites;
    }
}
