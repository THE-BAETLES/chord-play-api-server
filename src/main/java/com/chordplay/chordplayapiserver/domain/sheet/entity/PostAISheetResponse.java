package com.chordplay.chordplayapiserver.domain.sheet.entity;

import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class PostAISheetResponse {
    private int state;

    @Nullable
    private SheetInfo sheetInfo;
}
