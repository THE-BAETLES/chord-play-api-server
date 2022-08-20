package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.WatchHistoryResponse;

import java.util.List;

public interface VideoService {
    List<VideoResponse> search(String searchQuery);
    List<WatchHistoryResponse> getWatchHistory(int offset, int limit);
    Video create(String videoId);
    Video get(String videoId);


}
