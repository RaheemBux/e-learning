package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class ClassDTO extends AbstractDTO{

    private String id;
    private String name;
    private String duration;
    private String totalStudents;
    private VideoDTO videoDTO;
    private LikedCourseDTO likedCourseDTO;
}
