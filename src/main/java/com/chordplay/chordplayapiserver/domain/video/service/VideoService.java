package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;

import java.util.List;

public interface VideoService {
    List<VideoResponse> search(String searchQuery);
    List<VideoResponse> getWatchHistory(String offset, String limit);
    Video create(String videoId);
    Video get(String videoId);


}
