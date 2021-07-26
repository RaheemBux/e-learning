package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class UserDTO extends AbstractDTO {
    private String id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String surname;
    private String mobileNo;
    private String email;
    private String imagePath;
    private String type;
    private String version;
    private String wifi;
    private String quality;
    private String sex;
    private String job;
    private String studies;
    private Set<CourseDTO> courseDTOS;
    private VideoDTO videoDTO;

}
