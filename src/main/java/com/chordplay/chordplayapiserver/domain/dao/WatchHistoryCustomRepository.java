package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;

public interface WatchHistoryCustomRepository {
    void updateCountAndTimeByUserAndVideo(User user, Video video);
}
