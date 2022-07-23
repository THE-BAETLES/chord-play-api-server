package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.dto.FavoritesResponse;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinTempSocialRequest;

public interface UserService {
    public void join(JoinRequest dto);
    public void joinTempSocial(JoinTempSocialRequest dto);
    public void checkNicknameDuplication(String nickname);
    public FavoritesResponse getFavorites();
}
