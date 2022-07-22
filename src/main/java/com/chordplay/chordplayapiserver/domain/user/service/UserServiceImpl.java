package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.exception.NicknameDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void join(JoinRequest dto) {
        User user = (dto.getUsername().equals("admin")) ? dto.toEntity("ROLE_ADMIN") : dto.toEntity("ROLE_USER");
        userRepository.save(user);
        //ResponseEntity.status(404).body();
    }

    @Override
    public void checkNicknameDuplication(String nickname) {
        if(userRepository.existsByNickname(nickname) == false) throw new NicknameDuplicationException("이미 닉네임이 존재합니다");

    }
}
