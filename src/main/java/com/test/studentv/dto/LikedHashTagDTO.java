package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikedHashTagDTO extends AbstractDTO{

    private String id;
    private UserDTO userDTO;
    private HashTagDTO hashTagDTO;
    private String isLiked;
}
