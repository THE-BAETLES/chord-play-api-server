package com.chordplay.chordplayapiserver.dto.sheet;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SheetAiRequest {
    private String sheet_id;
    private String video_id;
    private int status;

    @Builder
    public SheetAiRequest(String sheet_id, String video_id, int status) {
        this.sheet_id = sheet_id;
        this.video_id = video_id;
        this.status = status;
    }

    public Sheet toEntity(User user){
        return Sheet.builder().user(user)
                .video(new Video(video_id))
                .build(); //title create_at update_at like_count
    }

}
