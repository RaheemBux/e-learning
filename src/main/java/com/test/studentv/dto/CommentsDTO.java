package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class CommentsDTO extends AbstractDTO{

    private String id;
    private String comment;
    // relationships
    private UserDTO userDTO;
    private CourseDTO courseDTO;
}
