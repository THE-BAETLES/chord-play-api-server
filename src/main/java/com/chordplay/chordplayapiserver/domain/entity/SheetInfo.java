package com.chordplay.chordplayapiserver.domain.sheet.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Date;

@Document(collection = "sheet_info")
public class SheetInfo {
    @Id
    private int id;

    private String videoId;

    private String userId;

    private String title;

    private Date createdAt;

    private Date updatedAt;

    private int likeCount;
}
