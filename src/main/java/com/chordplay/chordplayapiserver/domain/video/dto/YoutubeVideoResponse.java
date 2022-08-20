package com.chordplay.chordplayapiserver.domain.video.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

public class YoutubeVideoResponse {

    @JsonProperty("_id")
    private String id;
    private String thumbnailPath;
    private String title;
    private String genre;
    private String singer;
    private List<String> tag;


    public YoutubeVideoResponse() {
    }
}
