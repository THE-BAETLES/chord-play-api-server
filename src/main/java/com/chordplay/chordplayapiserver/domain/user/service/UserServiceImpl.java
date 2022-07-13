package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.TestUser;
import com.chordplay.chordplayapiserver.domain.user.TestUserRepository;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TestUserRepository testUserRepository;



    @Override
    public void join(JoinRequest dto) {
        String rawPassword = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        dto.setPassword(encPassword);
        TestUser testUser = (dto.getUsername().equals("admin")) ? dto.toEntity("ROLE_ADMIN") : dto.toEntity("ROLE_USER");
        testUserRepository.save(testUser);
    }
}
