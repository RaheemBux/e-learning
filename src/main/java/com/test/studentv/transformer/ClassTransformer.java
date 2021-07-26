package com.test.studentv.transformer;

import com.test.studentv.dto.ClassDTO;
import com.test.studentv.dto.LikedCourseDTO;
import com.test.studentv.dto.UserDTO;
import com.test.studentv.dto.VideoDTO;
import com.test.studentv.entity.ClassEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassTransformer {

    public static ClassDTO getClassDTO(ClassEntity classEntity){

        ClassDTO classDTO=new ClassDTO();

        if(classEntity.getId()!=null){
            classDTO.setId(classEntity.getId().toString());
        }
        if(classEntity.getName()!=null){
            classDTO.setName(classEntity.getName());
        }
        if(classEntity.getDuration()!=null){
            classDTO.setDuration(classEntity.getDuration());
        }
        if(classEntity.getTotalStudents()!=null){
            classDTO.setTotalStudents(classEntity.getTotalStudents());
        }
        if(classEntity.getVideoEntity()!=null){
            classDTO.setVideoDTO(VideoTransformer.getVideoDTO(classEntity.getVideoEntity()));
        }
        if(classEntity.getLikedCourseEntity()!=null){
            classDTO.setLikedCourseDTO(LikedCourseTransformer.getLikedCourseDTO(classEntity.getLikedCourseEntity()));
        }
        // five columns
        if(classEntity.getCreatedBy()!=null){
            classDTO.setCreatedBy(classEntity.getCreatedBy().toString());
        }
        if(classEntity.getModifiedBy()!=null){
            classDTO.setModifiedBy(classEntity.getModifiedBy().toString());
        }
        if(classEntity.getCreatedDate()!=null){
            classDTO.setCreatedDate(classEntity.getCreatedDate().toString());
        }
        if(classEntity.getModifiedDate()!=null){
            classDTO.setModifiedDate(classEntity.getModifiedDate().toString());
        }
        if(classEntity.getStatus()!=null){
            classDTO.setStatus(classEntity.getStatus().toString());
        }


        return classDTO;
    }
    public static ClassEntity getClassEntity(ClassDTO classDTO){

        ClassEntity classEntity=new ClassEntity();

        if(classDTO.getId()!=null){
            classEntity.setId(Long.parseLong(classDTO.getId()));
        }
        if(classDTO.getName()!=null){
            classEntity.setName(classDTO.getName());
        }
        if(classDTO.getDuration()!=null){
            classEntity.setDuration(classDTO.getDuration());
        }
        if(classDTO.getTotalStudents()!=null){
            classEntity.setTotalStudents(classDTO.getTotalStudents());
        }
        if(classDTO.getVideoDTO()!=null){
            classEntity.setVideoEntity(VideoTransformer.getVideoEntity(classDTO.getVideoDTO()));
        }
        if(classDTO.getLikedCourseDTO()!=null){
            classEntity.setLikedCourseEntity(LikedCourseTransformer.getLikedCourseEntity(classDTO.getLikedCourseDTO()));
        }
        // five columns
        if(classDTO.getCreatedBy()!=null){
            classEntity.setCreatedBy(Long.parseLong(classDTO.getCreatedBy()));
        }
        if(classDTO.getModifiedBy()!=null){
            classEntity.setModifiedBy(Long.parseLong(classDTO.getModifiedBy()));
        }
        classEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        classEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(classDTO.getStatus()!=null){
            classEntity.setStatus(Boolean.parseBoolean(classDTO.getStatus()));
        }
        return classEntity;
    }
    public static List<ClassDTO> getClassDTOs(List<ClassEntity> classEntities) {
        List<ClassDTO> classDTOS = new ArrayList<>();
        classEntities.forEach(classEntity-> {
            classDTOS.add(ClassTransformer.getClassDTO(classEntity));
        });
        return classDTOS;
    }
}
