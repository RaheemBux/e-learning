package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WatcherDTO extends AbstractDTO{
    private String id;
    private String min;
    private String sec;
    private String totalTime;
    private UserDTO userDTO;
    private VideoDTO videoDTO;

}
