package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.TestUser;
import com.chordplay.chordplayapiserver.domain.user.TestUserRepository;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TestUserRepository testUserRepository;

    @Override
    @Transactional
    public void join(JoinRequest dto) {
        TestUser testUser = (dto.getUsername().equals("admin")) ? dto.toEntity("ROLE_ADMIN") : dto.toEntity("ROLE_USER");
        testUserRepository.save(testUser);
    }
}
