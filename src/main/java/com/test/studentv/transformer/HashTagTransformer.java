package com.test.studentv.transformer;

import com.test.studentv.dto.CategoryDTO;
import com.test.studentv.dto.HashTagDTO;
import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.entity.HashTagEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class HashTagTransformer {

    public static HashTagEntity getHashTagEntity(HashTagDTO hashTagDTO){
        HashTagEntity hashTagEntity = new HashTagEntity();

        if(hashTagDTO.getId()!=null){
            hashTagEntity.setId(Long.parseLong(hashTagDTO.getId()));
        }
        if(hashTagDTO.getName()!=null){
            hashTagEntity.setName(hashTagDTO.getName());
        }
        if(hashTagDTO.getCode()!=null){
            hashTagEntity.setCode(hashTagDTO.getCode());
        }
        if(hashTagDTO.getClassDTO()!=null){
            hashTagEntity.setClassEntity(ClassTransformer.getClassEntity(hashTagDTO.getClassDTO()));
        }
        if(hashTagDTO.getCreatedBy()!=null){
            hashTagEntity.setCreatedBy(Long.parseLong(hashTagDTO.getCreatedBy()));
        }
        if(hashTagDTO.getModifiedBy()!=null){
            hashTagEntity.setModifiedBy(Long.parseLong(hashTagDTO.getModifiedBy()));
        }
        hashTagEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        hashTagEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(hashTagDTO.getStatus()!=null){
            hashTagEntity.setStatus(Boolean.valueOf(hashTagDTO.getStatus()));
        }

        return hashTagEntity;
    }
    public static HashTagDTO getHashTagDTO(HashTagEntity hashTagEntity){
        HashTagDTO hashTagDTO = new HashTagDTO();

        if(hashTagEntity.getId()!=null){
            hashTagDTO.setId(hashTagEntity.getId().toString());
        }
        if(hashTagEntity.getName()!=null){
            hashTagDTO.setName(hashTagEntity.getName());
        }
        if(hashTagEntity.getCode()!=null){
            hashTagDTO.setCode(hashTagEntity.getCode());
        }
        if(hashTagEntity.getClassEntity()!=null){
            hashTagDTO.setClassDTO(ClassTransformer.getClassDTO(hashTagEntity.getClassEntity()));
        }

        if(hashTagEntity.getCreatedBy()!=null){
            hashTagDTO.setCreatedBy(hashTagEntity.getCreatedBy().toString());
        }
        if(hashTagEntity.getModifiedBy()!=null){
            hashTagDTO.setModifiedBy(hashTagEntity.getModifiedBy().toString());
        }
        if(hashTagEntity.getCreatedDate()!=null){
            hashTagDTO.setCreatedDate(hashTagEntity.getCreatedDate().toString());
        }
        if(hashTagEntity.getModifiedDate()!=null){
            hashTagDTO.setModifiedDate(hashTagEntity.getModifiedDate().toString());
        }
        if(hashTagEntity.getStatus()!=null){
            hashTagDTO.setStatus(hashTagEntity.getStatus().toString());
        }

        return hashTagDTO;
    }
    public static List<HashTagDTO> getHashTagDTOS(List<HashTagEntity> hashTagEntities) {

        List<HashTagDTO> hashTagDTOS = new ArrayList<>();
        hashTagEntities.forEach(hashTagEntity -> {
            hashTagDTOS.add(HashTagTransformer.getHashTagDTO(hashTagEntity));
        });
        return hashTagDTOS;
    }
}
