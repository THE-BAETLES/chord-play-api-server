package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.user.dto.FavoritesResponse;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinTempSocialRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.SignupFavoriteRequest;
import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import com.chordplay.chordplayapiserver.domain.user.exception.UserNotFoundException;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoRequest;
import com.chordplay.chordplayapiserver.domain.video.service.VideoService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final VideoService videoService;

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
    }

    @Override
    @Transactional
    public void join(JoinRequest dto) {
        User user = (dto.getUsername().equals("admin")) ? dto.toEntity("ROLE_ADMIN") : dto.toEntity("ROLE_USER");
        userRepository.deleteByFirebaseUid(user.getFirebaseUid());  //임시
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void joinTempSocial(JoinTempSocialRequest dto) {
        User user = dto.toEntity();
        userRepository.save(user);
    }

    @Override
    public void checkNicknameDuplication(String nickname) {
        String LowerCaseNickname = nickname.toLowerCase();
        if(userRepository.existsByNickname(LowerCaseNickname)) throw new NicknameDuplicationException("이미 닉네임이 존재합니다");

    }

    @Override
    public String RecommendNickname(String userEmail){
        String subEmail = (userEmail.split("@"))[0];

        String recommendedNickName = subEmail;
        int num = 1;
        while(userRepository.existsByNickname(recommendedNickName)){
            recommendedNickName = subEmail + Integer.toString(num);
        }
        return recommendedNickName;
    }

    @Override
    public FavoritesResponse getFavorites() {
        List<Video> videos = Video.GetDummyVideos();
        return FavoritesResponse.builder().favorites(videos).build();
    }

    @Override
    public void createSignupFavorite(SignupFavoriteRequest req) {
        List<VideoRequest> videoRequests = req.getSignupFavorite();
        List<String> signupFavorite = new ArrayList<String>();
        for(VideoRequest videoRequest : videoRequests) {
            signupFavorite.add(videoRequest.getId());
        }
        String userId = ContextUtil.getPrincipalUserId();
        userRepository.findAndPushSignupFavoriteById(userId, signupFavorite);
    }

    @Override
    public void addVideoIdToMyCollection(String videoId) {
        Video video = videoService.create(videoId);
        User user = getUser(ContextUtil.getPrincipalUserId());
        userRepository.addVideoIdToCollectionById(user.getId(), video.getId());
    }
    @Override
    public void deleteVideoIdFromMyCollection(String videoId) {
        String userId = ContextUtil.getPrincipalUserId();
        userRepository.deleteVideoIdFromCollectionById(userId, videoId);
    }
}
