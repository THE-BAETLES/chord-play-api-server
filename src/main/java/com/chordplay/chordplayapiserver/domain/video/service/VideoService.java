package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;

import java.util.List;

public interface VideoService {
    List<VideoResponse> search(String search_query);
    void create(String video_id);
}
