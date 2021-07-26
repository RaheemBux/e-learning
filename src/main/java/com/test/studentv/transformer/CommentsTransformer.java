package com.test.studentv.transformer;

import com.test.studentv.dto.CommentsDTO;
import com.test.studentv.entity.CommentsEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class CommentsTransformer {

    public static CommentsDTO getCommentsDTO(CommentsEntity commentsEntity){
        CommentsDTO commentsDTO = new CommentsDTO();

        if(commentsEntity.getId()!=null){
            commentsDTO.setId(commentsEntity.getId().toString());
        }
        if(commentsEntity.getComment()!=null){
            commentsDTO.setComment(commentsEntity.getComment());
        }
        if(commentsEntity.getUserEntity()!=null){
            commentsDTO.setUserDTO(UserTransformer.getUserDTO(commentsEntity.getUserEntity()));
        }
        if(commentsEntity.getCourseEntity()!=null){
            commentsDTO.setCourseDTO(CourseTransformer.getCourseDTO(commentsEntity.getCourseEntity()));
        }
        // five columns
        if(commentsEntity.getCreatedBy()!=null){
            commentsDTO.setCreatedBy(commentsEntity.getCreatedBy().toString());
        }
        if(commentsEntity.getModifiedBy()!=null){
            commentsDTO.setModifiedBy(commentsEntity.getModifiedBy().toString());
        }
        if(commentsEntity.getCreatedDate()!=null){
            commentsDTO.setCreatedDate(commentsEntity.getCreatedDate().toString());
        }
        if(commentsEntity.getModifiedDate()!=null){
            commentsDTO.setModifiedDate(commentsEntity.getModifiedDate().toString());
        }
        if(commentsEntity.getStatus()!=null){
            commentsDTO.setStatus(commentsEntity.getStatus().toString());
        }

        return commentsDTO;
    }
    public static CommentsEntity getCommentsEntity(CommentsDTO commentsDTO){
        CommentsEntity commentsEntity = new CommentsEntity();

        if(commentsDTO.getId()!=null){
            commentsEntity.setId(Long.parseLong(commentsDTO.getId()));
        }
        if(commentsDTO.getComment()!=null){
            commentsEntity.setComment(commentsDTO.getComment());
        }
        if(commentsDTO.getUserDTO()!=null){
            commentsEntity.setUserEntity(UserTransformer.getUserEntity(commentsDTO.getUserDTO()));
        }
        if(commentsDTO.getCourseDTO()!=null){
            commentsEntity.setCourseEntity(CourseTransformer.getCourseEntity(commentsDTO.getCourseDTO()));
        }
        // five columns
        if(commentsDTO.getCreatedBy()!=null){
            commentsEntity.setCreatedBy(Long.parseLong(commentsDTO.getCreatedBy()));
        }
        if(commentsDTO.getModifiedBy()!=null){
            commentsEntity.setModifiedBy(Long.parseLong(commentsDTO.getModifiedBy()));
        }
        commentsEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        commentsEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(commentsDTO.getStatus()!=null){
            commentsEntity.setStatus(Boolean.parseBoolean(commentsDTO.getStatus()));
        }
        return commentsEntity;
    }
    public static List<CommentsDTO> getCommentsDTOs(List<CommentsEntity> commentsEntities) {
        List<CommentsDTO> commentsDTOS = new ArrayList<>();
        commentsEntities.forEach(commentsEntity-> {
            commentsDTOS.add(getCommentsDTO(commentsEntity));
        });
        return commentsDTOS;
    }
}
