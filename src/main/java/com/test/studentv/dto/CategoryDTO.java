package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends AbstractDTO{
    private String id;
    private String name;
    private String code;
}
