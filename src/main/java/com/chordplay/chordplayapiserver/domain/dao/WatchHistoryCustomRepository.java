package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;

import java.util.List;

public interface WatchHistoryCustomRepository {
    void updateCountAndTimeByUserAndVideo(User user, Video video);
    void updateCountAndTimeByUserAndVideo(String userId, String videoId);
    List<WatchHistory> findByIdWithOffsetAndLimit(String userId, int offset, int limit);
}
