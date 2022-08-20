package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.dto.FavoritesResponse;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinTempSocialRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.SignupFavoriteRequest;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoRequest;

import java.util.List;

public interface UserService {
    public void join(JoinRequest dto);
    public void joinTempSocial(JoinTempSocialRequest dto);
    public void checkNicknameDuplication(String nickname);
    public String RecommendNickname(String userEmail);
    public FavoritesResponse getFavorites();
    public void createSignupFavorite(SignupFavoriteRequest req);
}
