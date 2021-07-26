package com.test.studentv.transformer;

import com.test.studentv.dto.LikedHashTagDTO;
import com.test.studentv.entity.LikedHashTagEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class LikedHashTagTransformer {

    public static LikedHashTagDTO getLikedHashTagDTO(LikedHashTagEntity likedHashTagEntity){

        LikedHashTagDTO likedHashTagDTO = new LikedHashTagDTO();

        if(likedHashTagEntity.getId()!=null){
            likedHashTagDTO.setId(likedHashTagEntity.getId().toString());
        }
        if(likedHashTagEntity.getUserEntity()!=null){
            likedHashTagDTO.setUserDTO(UserTransformer.getUserDTO(likedHashTagEntity.getUserEntity()));
        }
        if(likedHashTagEntity.getHashTagEntity()!=null){
            likedHashTagDTO.setHashTagDTO(HashTagTransformer.getHashTagDTO(likedHashTagEntity.getHashTagEntity()));
        }
        if(likedHashTagEntity.getIsLiked()!=null){
            likedHashTagDTO.setIsLiked(likedHashTagEntity.getIsLiked().toString());
        }
        if(likedHashTagEntity.getCreatedBy()!=null){
            likedHashTagDTO.setCreatedBy(likedHashTagEntity.getCreatedBy().toString());
        }
        if(likedHashTagEntity.getModifiedBy()!=null){
            likedHashTagDTO.setModifiedBy(likedHashTagEntity.getModifiedBy().toString());
        }
        if(likedHashTagEntity.getCreatedDate()!=null){
            likedHashTagDTO.setCreatedDate(likedHashTagEntity.getCreatedDate().toString());
        }
        if(likedHashTagEntity.getModifiedDate()!=null){
            likedHashTagDTO.setModifiedDate(likedHashTagEntity.getModifiedDate().toString());
        }
        if(likedHashTagEntity.getStatus()!=null){
            likedHashTagDTO.setStatus(likedHashTagEntity.getStatus().toString());
        }

        return likedHashTagDTO;
    }
    public static LikedHashTagEntity getLikedHashTagEntity(LikedHashTagDTO likedHashTagDTO){

        LikedHashTagEntity likedHashTagEntity = new LikedHashTagEntity();

        if(likedHashTagDTO.getId()!=null){
            likedHashTagEntity.setId(Long.parseLong(likedHashTagDTO.getId()));
        }
        if(likedHashTagDTO.getUserDTO()!=null){
            likedHashTagEntity.setUserEntity(UserTransformer.getUserEntity(likedHashTagDTO.getUserDTO()));
        }
        if(likedHashTagDTO.getHashTagDTO()!=null){
            likedHashTagEntity.setHashTagEntity(HashTagTransformer.getHashTagEntity(likedHashTagDTO.getHashTagDTO()));
        }
        if(likedHashTagDTO.getIsLiked()!=null){
            likedHashTagEntity.setIsLiked(Boolean.valueOf(likedHashTagDTO.getIsLiked()));
        }
        if(likedHashTagDTO.getModifiedBy()!=null){
            likedHashTagEntity.setModifiedBy(Long.parseLong(likedHashTagDTO.getModifiedBy()));
        }
        likedHashTagEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        likedHashTagEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(likedHashTagDTO.getStatus()!=null){
            likedHashTagEntity.setStatus(Boolean.valueOf(likedHashTagDTO.getStatus()));
        }

        return likedHashTagEntity;
    }
    public static List<LikedHashTagDTO> getLikedHashTagDTOs(List<LikedHashTagEntity> likedHashTagEntities) {
        List<LikedHashTagDTO> likedHashTagDTOS = new ArrayList<>();
        likedHashTagEntities.forEach(likedHashTagEntity -> {
            likedHashTagDTOS.add(getLikedHashTagDTO(likedHashTagEntity));
        });
        return likedHashTagDTOS;
    }
}
