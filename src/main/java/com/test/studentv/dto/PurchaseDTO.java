package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDTO extends AbstractDTO{

    private String id;
    private String purchaseDate;
    private String price;
    private UserDTO userDTO;
    private CourseDTO courseDTO;

}
