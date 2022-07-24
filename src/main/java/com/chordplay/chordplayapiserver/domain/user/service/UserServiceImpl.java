package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.user.dto.FavoritesResponse;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinTempSocialRequest;
import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

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
        if(userRepository.existsByNickname(nickname)) throw new NicknameDuplicationException("이미 닉네임이 존재합니다");

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
        List<Video> videos = new ArrayList<Video>();
        Video exampleVideo = Video.builder().id("HYUNJUN").difficulty_avg(3).genre("zz").play_count(3).singer("배찬우")
                .title("벛꽃엔딩").thumnail_path("https://i.ytimg.com/vi/q_WMoSdsXRk/hqdefault.jpg?sqp=-oaymwEWCMACELQBIAQqCghQEJADGFogjgJIWg&rs=AOn4CLA1X0WqNqn5pcUEGDv7qwLgLBgQYw").build();
        videos.add(exampleVideo);
        return FavoritesResponse.builder().favorites(videos).build();
    }
}
