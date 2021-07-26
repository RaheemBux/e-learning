package com.test.studentv.transformer;

import com.test.studentv.dto.LikedCourseDTO;
import com.test.studentv.entity.LikedCourseEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class LikedCourseTransformer {

    public static LikedCourseDTO getLikedCourseDTO(LikedCourseEntity likedCourseEntity){
        LikedCourseDTO likedCourseDTO = new LikedCourseDTO();

        if(likedCourseEntity.getId()!=null){
            likedCourseDTO.setId(likedCourseEntity.getId().toString());
        }
        if(likedCourseEntity.getUserEntity()!=null){
           likedCourseDTO.setUserDTO(UserTransformer.getUserDTO(likedCourseEntity.getUserEntity()));
        }
        if(likedCourseEntity.getCourseEntity()!=null){
            likedCourseDTO.setCourseDTO(CourseTransformer.getCourseDTO(likedCourseEntity.getCourseEntity()));
        }
        if(likedCourseEntity.getIsLiked()!=null){
            likedCourseDTO.setIsLiked(likedCourseEntity.getIsLiked().toString());
        }
        if(likedCourseEntity.getCreatedBy()!=null){
            likedCourseDTO.setCreatedBy(likedCourseEntity.getCreatedBy().toString());
        }
        if(likedCourseEntity.getModifiedBy()!=null){
            likedCourseDTO.setModifiedBy(likedCourseEntity.getModifiedBy().toString());
        }
        if(likedCourseEntity.getCreatedDate()!=null){
            likedCourseDTO.setCreatedDate(likedCourseEntity.getCreatedDate().toString());
        }
        if(likedCourseEntity.getModifiedDate()!=null){
            likedCourseDTO.setModifiedDate(likedCourseEntity.getModifiedDate().toString());
        }
        if(likedCourseEntity.getStatus()!=null){
            likedCourseDTO.setStatus(likedCourseEntity.getStatus().toString());
        }

        return likedCourseDTO;

    }
    public static LikedCourseEntity getLikedCourseEntity(LikedCourseDTO likedCourseDTO){
        LikedCourseEntity likedCourseEntity = new LikedCourseEntity();

        if(likedCourseDTO.getId()!=null){
            likedCourseEntity.setId(Long.parseLong(likedCourseDTO.getId()));
        }
        if(likedCourseDTO.getUserDTO()!=null){
            likedCourseEntity.setUserEntity(UserTransformer.getUserEntity(likedCourseDTO.getUserDTO()));
        }
        if(likedCourseDTO.getCourseDTO()!=null){
            likedCourseEntity.setCourseEntity(CourseTransformer.getCourseEntity(likedCourseDTO.getCourseDTO()));
        }
        if(likedCourseDTO.getIsLiked()!=null){
            likedCourseEntity.setIsLiked(Boolean.valueOf(likedCourseDTO.getIsLiked()));
        }
        if(likedCourseDTO.getModifiedBy()!=null){
            likedCourseEntity.setModifiedBy(Long.parseLong(likedCourseDTO.getModifiedBy()));
        }
        likedCourseEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        likedCourseEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(likedCourseDTO.getStatus()!=null){
            likedCourseEntity.setStatus(Boolean.valueOf(likedCourseDTO.getStatus()));
        }

        return likedCourseEntity;

    }
    public static List<LikedCourseDTO> getLikedCourseDTOS(List<LikedCourseEntity> likedCourseEntities) {
        List<LikedCourseDTO> likedCourseDTOS = new ArrayList<>();
        likedCourseEntities.forEach(likedCourseEntity -> {
            likedCourseDTOS.add(LikedCourseTransformer.getLikedCourseDTO(likedCourseEntity));
        });
        return likedCourseDTOS;
    }
}
