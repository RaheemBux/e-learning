package com.test.studentv.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDTO extends AbstractDTO{

    private String id;
    private String order;
    private String title;
    private String section;
    private String description;
    private String length;
    private String url;
    private String pdf;
    private String videoPath;
    private HashTagDTO hashTagDTO;
}
