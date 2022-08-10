package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    YoutubeVideoSearch youtubeVideoSearch;

    @Override
    public void search(String search_query) {
        List<Video> videos = youtubeVideoSearch.search_KR(search_query);

    }
}
