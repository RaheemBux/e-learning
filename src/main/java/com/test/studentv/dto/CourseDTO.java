package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CourseDTO extends AbstractDTO {

    private String id;
    private String name;
    private String code;
    private String level;
    private String description;
    private String price;
    private String githubLink;
    private String publicityImage;
    private String completion;
    private CategoryDTO categoryDTO;
    private ClassDTO classDTO;
    private Set<UserDTO> userDTOS;
    private Set<CardsDTO> cardsDTOS;
}
