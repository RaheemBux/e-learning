package com.test.studentv.transformer;

import com.test.studentv.dto.LikedCategoryDTO;
import com.test.studentv.entity.LikedCategoryEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class LikedCategoryTransformer {

    public static LikedCategoryDTO getLikedCategoryDTO(LikedCategoryEntity likedCategoryEntity){

        LikedCategoryDTO likedCategoryDTO = new LikedCategoryDTO();

        if(likedCategoryEntity.getId()!=null){
            likedCategoryDTO.setId(likedCategoryEntity.getId().toString());
        }
        if(likedCategoryEntity.getUserEntity()!=null){
            likedCategoryDTO.setUserDTO(UserTransformer.getUserDTO(likedCategoryEntity.getUserEntity()));
        }
        if(likedCategoryEntity.getCategoryEntity()!=null){
            likedCategoryDTO.setCategoryDTO(CategoryTransformer.getCategoryDTO(likedCategoryEntity.getCategoryEntity()));
        }
        if(likedCategoryEntity.getIsLiked()!=null){
            likedCategoryDTO.setIsLiked(likedCategoryEntity.getIsLiked().toString());
        }
        if(likedCategoryEntity.getCreatedBy()!=null){
            likedCategoryDTO.setCreatedBy(likedCategoryEntity.getCreatedBy().toString());
        }
        if(likedCategoryEntity.getModifiedBy()!=null){
            likedCategoryDTO.setModifiedBy(likedCategoryEntity.getModifiedBy().toString());
        }
        if(likedCategoryEntity.getCreatedDate()!=null){
            likedCategoryDTO.setCreatedDate(likedCategoryEntity.getCreatedDate().toString());
        }
        if(likedCategoryEntity.getModifiedDate()!=null){
            likedCategoryDTO.setModifiedDate(likedCategoryEntity.getModifiedDate().toString());
        }
        if(likedCategoryEntity.getStatus()!=null){
            likedCategoryDTO.setStatus(likedCategoryEntity.getStatus().toString());
        }


        return likedCategoryDTO;
    }
    public static LikedCategoryEntity getLikedCategoryEntity(LikedCategoryDTO likedCategoryDTO){

        LikedCategoryEntity likedCategoryEntity = new LikedCategoryEntity();

        if(likedCategoryDTO.getId()!=null){
            likedCategoryEntity.setId(Long.parseLong(likedCategoryDTO.getId()));
        }
        if(likedCategoryDTO.getUserDTO()!=null){
            likedCategoryEntity.setUserEntity(UserTransformer.getUserEntity(likedCategoryDTO.getUserDTO()));
        }
        if(likedCategoryDTO.getCategoryDTO()!=null){
            likedCategoryEntity.setCategoryEntity(CategoryTransformer.getCategoryEntity(likedCategoryDTO.getCategoryDTO()));
        }
        if(likedCategoryDTO.getIsLiked()!=null){
            likedCategoryEntity.setIsLiked(Boolean.valueOf(likedCategoryDTO.getIsLiked()));
        }
        if(likedCategoryDTO.getModifiedBy()!=null){
            likedCategoryEntity.setModifiedBy(Long.parseLong(likedCategoryDTO.getModifiedBy()));
        }
        likedCategoryEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        likedCategoryEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(likedCategoryDTO.getStatus()!=null){
            likedCategoryEntity.setStatus(Boolean.valueOf(likedCategoryDTO.getStatus()));
        }

        return likedCategoryEntity;
    }
    public static List<LikedCategoryDTO> getLikedCategoryDTOs(List<LikedCategoryEntity> likedCategoryEntities) {
        List<LikedCategoryDTO> likedCategoryDTOS = new ArrayList<>();
        likedCategoryEntities.forEach(likedCategoryEntity -> {
            likedCategoryDTOS.add(getLikedCategoryDTO(likedCategoryEntity));
        });
        return likedCategoryDTOS;
    }
}
