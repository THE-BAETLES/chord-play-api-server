package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.dao.VideoRepository;
import com.chordplay.chordplayapiserver.domain.dao.WatchHistoryRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.WatchHistoryResponse;
import com.chordplay.chordplayapiserver.domain.video.exception.VideoNotFoundException;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.google.api.Context;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    private final YoutubeVideoSearch youtubeVideoSearch;
    private final VideoRepository videoRepository;
    private final SheetService sheetService;
    private final WatchHistoryRepository watchHistoryRepository;
    @Override
    public Video create(String videoId) {
        Optional<Video> videoOptional = videoRepository.findById(videoId);
        if(videoOptional.isPresent()) return videoOptional.get();
        com.google.api.services.youtube.model.Video youtubeVideo = youtubeVideoSearch.getYoutubeVideoInfo(videoId);
        Video video = new Video(youtubeVideo);
        videoRepository.save(video);

        sheetService.createOnlySheet(video.getId());
        return video;
    }

    @Override
    public List<VideoResponse> search(String searchQuery) {
        List<VideoResponse> videoResponses = new ArrayList<VideoResponse>();

        List<SearchResult> youtubeSearchResults = youtubeVideoSearch.search_KR(searchQuery);
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

    @Override
    public List<WatchHistoryResponse> getWatchHistory(int offset, int limit) {
        List<WatchHistory> watchHistories = null;
        String userId = ContextUtil.getPrincipalUserId();
        watchHistories = watchHistoryRepository.findByIdWithOffsetAndLimit(userId, offset, limit);
        List<WatchHistoryResponse> watchHistoryResponses = new ArrayList<WatchHistoryResponse>();

        for (WatchHistory w : watchHistories){
            watchHistoryResponses.add(new WatchHistoryResponse(w));
        }

        return watchHistoryResponses;
    }

    @Override
    public Video get(String videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException());
    }
}
