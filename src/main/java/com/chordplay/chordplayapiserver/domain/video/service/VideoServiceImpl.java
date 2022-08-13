package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.dao.VideoRepository;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    private final YoutubeVideoSearch youtubeVideoSearch;
    private final VideoRepository videoRepository;


    @Override
    public void create(String video_id) {
        com.google.api.services.youtube.model.Video youtubeVideo = youtubeVideoSearch.getYoutubeVideoInfo(video_id);
        Video video = new Video(youtubeVideo);
        videoRepository.save(video);
    }

    @Override
    public List<VideoResponse> search(String search_query) {
        List<VideoResponse> videoResponses = new ArrayList<VideoResponse>();

        List<SearchResult> youtubeSearchResults = youtubeVideoSearch.search_KR(search_query);
        for (SearchResult youtubeSearchResult : youtubeSearchResults) {

            VideoResponse videoResponse = null;

            Optional<Video> optVideo = videoRepository.findById(youtubeSearchResult.getId().getVideoId());
            if (optVideo.isPresent()) {
                Video video = optVideo.get();
                Long sheetCount = videoRepository.getSheetCount(video.getId()) ;
                videoResponse = new VideoResponse(video, sheetCount);
            } else {
                videoResponse = new VideoResponse(youtubeSearchResult);
            }

            videoResponses.add(videoResponse);
        }

        return videoResponses;
    }

}
