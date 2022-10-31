package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.video.dto.VideoResponse;
import com.chordplay.chordplayapiserver.domain.video.dto.WatchHistoryResponse;

import java.util.List;

public interface VideoService {
    List<VideoResponse> search(String searchQuery);
    List<VideoResponse> getWatchHistory(int offset, int limit);
    List<VideoResponse> getGradeCollection(String performerGrade);
    Video create(String videoId);
    Video get(String videoId);
    Boolean isInMyCollection(String videoId);
    Long getSheetCount(String videoId);
    VideoResponse toVideoResponse(Video video);

}
