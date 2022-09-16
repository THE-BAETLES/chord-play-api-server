package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.dao.VideoRepository;
import com.chordplay.chordplayapiserver.domain.dao.WatchHistoryRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import com.chordplay.chordplayapiserver.domain.entity.item.PerformerGrade;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.WatchHistoryResponse;
import com.chordplay.chordplayapiserver.domain.video.exception.IncorrectGradeInputException;
import com.chordplay.chordplayapiserver.domain.video.exception.VideoNotFoundException;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.google.api.Context;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService{

    private final YoutubeVideoSearch youtubeVideoSearch;
    private final VideoRepository videoRepository;
    private final SheetService sheetService;
    private final WatchHistoryRepository watchHistoryRepository;

    protected static final List<String> beginnerList = Arrays.asList("fbmStVcCL8s", "Gh-NrqgNd5E", "VY_BU1Ja_TM", "G-ujL8GsIFM", "0M4QMVQJRsw", "XZ8XsvGLoNE", "M7X8W1HbtRQ", "gWvQrC_wjCI", "BKNYZpVo0Vo");
    protected static final List<String> intermediateList = Arrays.asList("Nx3h23xCsbA", "Wk3Vxt3knnI", "0EK_M2taRIM", "R9TPed_ohKM", "OlXr5YD-MWA", "btGlnFgCVX8", "c30ePrP5oi4", "TZPjRVScdkk", "t7hmovsG_f0");
    protected static final List<String> expertList = Arrays.asList("QiziJ40kTz0", "kdOS94IjzzE", "hh5GKVa8VtM", "-uOShlFu1v8", "ghrlZIMDzbM", "r_RAv0JMB8s", "NF7oYY7Lhq4", "MBbqqWqLPlI", "aFoqCI75WoY");

    protected static final String BEGINNER = "beginner";
    protected static final String INTERMEDIATE = "intermediate";
    protected static final String EXPERT = "expert";


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
    public List<VideoResponse> getGradeCollection(String performerGrade) {
        List<VideoResponse> videoResponses = new ArrayList<VideoResponse>();
        List<Video> videos = null;
        if (performerGrade.equals(BEGINNER)) {
            videos = getVideosByList(beginnerList);

        } else if (performerGrade.equals(INTERMEDIATE)) {
            videos = getVideosByList(intermediateList);
        } else if (performerGrade.equals(EXPERT)) {
            videos = getVideosByList(expertList);
        } else{
            throw new IncorrectGradeInputException();
        }

        for (Video video : videos){
            System.out.println(video.getId());
            videoResponses.add(new VideoResponse(video));
        }
        return videoResponses;
    }

    @Override
    public List<VideoResponse> getWatchHistory(int offset, int limit) {
        List<WatchHistory> watchHistories = null;
        String userId = ContextUtil.getPrincipalUserId();
        watchHistories = watchHistoryRepository.findByIdWithOffsetAndLimit(userId, offset, limit);
        List<VideoResponse> videoResponses = new ArrayList<VideoResponse>();

        for (WatchHistory w : watchHistories){
            videoResponses.add(new VideoResponse(w.getVideo()));
        }
        return videoResponses;
    }

    @Override
    public Video get(String videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException());
    }


    private List<Video> getVideosByList(List<String> videoIdList){
        List<Video> videos = new ArrayList<Video>();
        for (String videoId : videoIdList) {
            Optional<Video> videoOptional = videoRepository.findById(videoId);
            if (videoOptional.isPresent()){
                videos.add(videoOptional.get());
            } else {
                videos.add(create(videoId));
            }
        }
        return videos;
    }
}
