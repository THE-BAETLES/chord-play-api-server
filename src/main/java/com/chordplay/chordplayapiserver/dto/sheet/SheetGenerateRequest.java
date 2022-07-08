package com.chordplay.chordplayapiserver.dto.sheet;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SheetGenerateRequest {
    private String sheet_id;
    private String vid_youtube;
    private int status;

    @Builder
    public SheetGenerateRequest(String sheet_id, String vid_youtube, int status) {
        this.sheet_id = sheet_id;
        this.vid_youtube = vid_youtube;
        this.status = status;
    }
}
