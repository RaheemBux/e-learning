package com.test.studentv.transformer;

import com.test.studentv.dto.CourseDTO;
import com.test.studentv.dto.UserDTO;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.util.CommonUtil;
import com.test.studentv.util.EncoderDecoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTransformer {

    public static UserDTO getUserDTO(UserEntity userEntity){
        UserDTO userDTO=new UserDTO();
        if(userEntity.getId()!=null){
            userDTO.setId(userEntity.getId().toString());
        }
        if(userEntity.getUserName()!=null){
            userDTO.setUserName(userEntity.getUserName());
        }
        if(userEntity.getFirstName()!=null){
            userDTO.setFirstName(userEntity.getFirstName());
        }
        if(userEntity.getLastName()!=null){
            userDTO.setLastName(userEntity.getLastName());
        }
        if(userEntity.getSurname()!=null){
            userDTO.setSurname(userEntity.getSurname());
        }
        if(userEntity.getEmail()!=null){
            userDTO.setEmail(userEntity.getEmail());
        }
        if(userEntity.getMobileNo()!=null){
            userDTO.setMobileNo(userEntity.getMobileNo());
        }
        if(userEntity.getImagePath()!=null){
            userDTO.setImagePath(userEntity.getImagePath());
        }
        if(userEntity.getType()!=null){
            userDTO.setType(userEntity.getType());
        }
        if(userEntity.getVersion()!=null){
            userDTO.setVersion(userEntity.getVersion());
        }
        if(userEntity.getWifi()!=null){
            userDTO.setWifi(userEntity.getWifi());
        }
        if(userEntity.getSex()!=null){
            userDTO.setSex(userEntity.getSex());
        }
        if(userEntity.getQuality()!=null){
            userDTO.setQuality(userEntity.getQuality());
        }
        if(userEntity.getJob()!=null){
            userDTO.setJob(userEntity.getJob());
        }
        if(userEntity.getStudies()!=null){
            userDTO.setStudies(userEntity.getStudies());
        }
        if(userEntity.getCourseEntities()!=null){

            Set<CourseDTO> courseDTOS = new HashSet<>();
            for(CourseEntity courseEntity : userEntity.getCourseEntities()){
                courseDTOS.add(CourseTransformer.getCourseDTOWithoutUser(courseEntity));
            }
            userDTO.setCourseDTOS(courseDTOS);
        }
        if(userEntity.getVideoEntity()!=null){
            userDTO.setVideoDTO(VideoTransformer.getVideoDTO(userEntity.getVideoEntity()));
        }
        // five columns
        if(userEntity.getCreatedBy()!=null){
            userDTO.setCreatedBy(userEntity.getCreatedBy().toString());
        }
        if(userEntity.getModifiedBy()!=null){
            userDTO.setModifiedBy(userEntity.getModifiedBy().toString());
        }
        if(userEntity.getCreatedDate()!=null){
            userDTO.setCreatedDate(userEntity.getCreatedDate().toString());
        }
        if(userEntity.getModifiedDate()!=null){
            userDTO.setModifiedDate(userEntity.getModifiedDate().toString());
        }
        if(userEntity.getStatus()!=null){
            userDTO.setStatus(userEntity.getStatus().toString());
        }
        return userDTO;
    }
    public static UserDTO getUserDTOWithoutCourse(UserEntity userEntity){
        UserDTO userDTO=new UserDTO();
        if(userEntity.getId()!=null){
            userDTO.setId(userEntity.getId().toString());
        }
        if(userEntity.getUserName()!=null){
            userDTO.setUserName(userEntity.getUserName());
        }
        if(userEntity.getFirstName()!=null){
            userDTO.setFirstName(userEntity.getFirstName());
        }
        if(userEntity.getLastName()!=null){
            userDTO.setLastName(userEntity.getLastName());
        }
        if(userEntity.getSurname()!=null){
            userDTO.setSurname(userEntity.getSurname());
        }
        if(userEntity.getEmail()!=null){
            userDTO.setEmail(userEntity.getEmail());
        }
        if(userEntity.getSurname()!=null){
            userDTO.setSurname(userEntity.getSurname());
        }
        if(userEntity.getMobileNo()!=null){
            userDTO.setMobileNo(userEntity.getMobileNo());
        }
        if(userEntity.getImagePath()!=null){
            userDTO.setImagePath(userEntity.getImagePath());
        }
        if(userEntity.getType()!=null){
            userDTO.setType(userEntity.getType());
        }
        if(userEntity.getVersion()!=null){
            userDTO.setVersion(userEntity.getVersion());
        }
        if(userEntity.getWifi()!=null){
            userDTO.setWifi(userEntity.getWifi());
        }
        if(userEntity.getSex()!=null){
            userDTO.setSex(userEntity.getSex());
        }
        if(userEntity.getQuality()!=null){
            userDTO.setQuality(userEntity.getQuality());
        }
        if(userEntity.getJob()!=null){
            userDTO.setJob(userEntity.getJob());
        }
        if(userEntity.getStudies()!=null){
            userDTO.setStudies(userEntity.getStudies());
        }
        if(userEntity.getVideoEntity()!=null){
            userDTO.setVideoDTO(VideoTransformer.getVideoDTO(userEntity.getVideoEntity()));
        }

        // five columns
        if(userEntity.getCreatedBy()!=null){
            userDTO.setCreatedBy(userEntity.getCreatedBy().toString());
        }
        if(userEntity.getModifiedBy()!=null){
            userDTO.setModifiedBy(userEntity.getModifiedBy().toString());
        }
        if(userEntity.getCreatedDate()!=null){
            userDTO.setCreatedDate(userEntity.getCreatedDate().toString());
        }
        if(userEntity.getModifiedDate()!=null){
            userDTO.setModifiedDate(userEntity.getModifiedDate().toString());
        }
        if(userEntity.getStatus()!=null){
            userDTO.setStatus(userEntity.getStatus().toString());
        }
        return userDTO;
    }
    public static UserEntity getUserEntity(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();

        if(userDTO.getId()!=null){
            userEntity.setId(Long.parseLong(userDTO.getId()));
        }
        if(userDTO.getUserName()!=null){
            userEntity.setUserName(userDTO.getUserName());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().trim().equals("")) {
            userEntity.setPassword(EncoderDecoder.getEncryptedSHA1Password(userDTO.getPassword().trim()));
        }
        if(userDTO.getFirstName()!=null){
            userEntity.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName()!=null){
            userEntity.setLastName(userDTO.getLastName());
        }
        if(userDTO.getSurname()!=null){
            userEntity.setSurname(userDTO.getSurname());
        }
        if(userDTO.getEmail()!=null){
            userEntity.setEmail(userDTO.getEmail());
        }
        if(userDTO.getMobileNo()!=null){
            userEntity.setMobileNo(userDTO.getMobileNo());
        }
        if(userDTO.getType()!=null){
            userEntity.setType(userDTO.getType());
        }
        if(userDTO.getImagePath()!=null){
            userEntity.setImagePath(userDTO.getImagePath());
        }
        if(userDTO.getVersion()!=null){
            userEntity.setVersion(userDTO.getVersion());
        }
        if(userDTO.getWifi()!=null){
            userEntity.setWifi(userDTO.getWifi());
        }
        if(userDTO.getSex()!=null){
            userEntity.setSex(userDTO.getSex());
        }
        if(userDTO.getQuality()!=null){
            userEntity.setQuality(userDTO.getQuality());
        }
        if(userDTO.getJob()!=null){
            userEntity.setJob(userDTO.getJob());
        }
        if(userDTO.getStudies()!=null){
            userEntity.setStudies(userDTO.getStudies());
        }
        // five columns
        if(userDTO.getCreatedBy()!=null){
            userEntity.setCreatedBy(Long.parseLong(userDTO.getCreatedBy()));
        }
        if(userDTO.getModifiedBy()!=null){
            userEntity.setModifiedBy(Long.parseLong(userDTO.getModifiedBy()));
        }
        userEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        userEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(userDTO.getStatus()!=null){
            userEntity.setStatus(Boolean.parseBoolean(userDTO.getStatus()));
        }
        return userEntity;
    }
    public static List<UserDTO> getUserDTOs(List<UserEntity> userEntityList) {
        List<UserDTO> userDTOS = new ArrayList<>();
        userEntityList.forEach(userEntity -> {
            userDTOS.add(UserTransformer.getUserDTO(userEntity));
        });
        return userDTOS;
    }
}
