package com.test.studentv.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashTagDTO extends AbstractDTO{
    private String id;
    private String name;
    private String code;
    private ClassDTO classDTO;
}
