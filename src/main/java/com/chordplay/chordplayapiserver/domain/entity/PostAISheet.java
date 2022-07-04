package com.chordplay.chordplayapiserver.domain.sheet.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class PostAISheet {
    @NonNull
    private String videoId;
    @NonNull
    private int state;
}
