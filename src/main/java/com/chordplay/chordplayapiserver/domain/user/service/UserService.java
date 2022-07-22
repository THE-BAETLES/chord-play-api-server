package com.chordplay.chordplayapiserver.domain.user.service;

import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;

public interface UserService {
    public void join(JoinRequest dto);
    public void checkNicknameDuplication(String nickname);
}
