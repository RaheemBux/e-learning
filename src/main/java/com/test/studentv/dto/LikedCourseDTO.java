package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikedCourseDTO extends AbstractDTO{

    private String id;
    private UserDTO userDTO;
    private CourseDTO courseDTO;
    private String isLiked;
}
