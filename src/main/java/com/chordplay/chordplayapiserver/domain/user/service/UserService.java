package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.user.dto.*;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoRequest;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;

import java.util.List;

public interface UserService {
    public void join(JoinRequest dto);
    public void joinTempSocial(JoinTempSocialRequest dto);
    public void checkNicknameDuplication(String nickname);
    public String RecommendNickname(String userEmail);
    public FavoritesResponse getFavorites();
    public void createSignupFavorite(SignupFavoriteRequest req);

    public User getUser(String userId);
    void addVideoIdToMyCollection(String videoId);
    void deleteVideoIdFromMyCollection(String videoId);

    List<VideoResponse> getMyCollection();
    List<String> getMyCollectionVideoIdList();

    UserInformationResponse getUserInfo(String userId);
}
