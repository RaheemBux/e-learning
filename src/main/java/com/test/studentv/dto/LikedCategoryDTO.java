package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikedCategoryDTO extends AbstractDTO{

    private String id;
    private UserDTO userDTO;
    private CategoryDTO categoryDTO;
    private String isLiked;
}
