package com.test.studentv.transformer;

import com.test.studentv.dto.CategoryDTO;
import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryTransformer {

    public static CategoryDTO getCategoryDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO=new CategoryDTO();

        if(categoryEntity.getId()!=null){
            categoryDTO.setId(categoryEntity.getId().toString());
        }
        if(categoryEntity.getName()!=null){
            categoryDTO.setName(categoryEntity.getName());
        }
        if(categoryEntity.getCode()!=null){
            categoryDTO.setCode(categoryEntity.getCode());
        }

        if(categoryEntity.getCreatedBy()!=null){
            categoryDTO.setCreatedBy(categoryEntity.getCreatedBy().toString());
        }
        if(categoryEntity.getModifiedBy()!=null){
            categoryDTO.setModifiedBy(categoryEntity.getModifiedBy().toString());
        }
        if(categoryEntity.getCreatedDate()!=null){
            categoryDTO.setCreatedDate(categoryEntity.getCreatedDate().toString());
        }
        if(categoryEntity.getModifiedDate()!=null){
            categoryDTO.setModifiedDate(categoryEntity.getModifiedDate().toString());
        }
        if(categoryEntity.getStatus()!=null){
            categoryDTO.setStatus(categoryEntity.getStatus().toString());
        }

        return categoryDTO;

    }
    public static CategoryEntity getCategoryEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity=new CategoryEntity();

        if(categoryDTO.getId()!=null){
            categoryEntity.setId(Long.parseLong(categoryDTO.getId()));
        }
        if(categoryDTO.getName()!=null){
            categoryEntity.setName(categoryDTO.getName());
        }
        if(categoryDTO.getCode()!=null){
            categoryEntity.setCode(categoryDTO.getCode());
        }
        if(categoryDTO.getCreatedBy()!=null){
            categoryEntity.setCreatedBy(Long.parseLong(categoryDTO.getCreatedBy()));
        }
        if(categoryDTO.getModifiedBy()!=null){
            categoryEntity.setModifiedBy(Long.parseLong(categoryDTO.getModifiedBy()));
        }
        categoryEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        categoryEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(categoryDTO.getStatus()!=null){
            categoryEntity.setStatus(Boolean.valueOf(categoryDTO.getStatus()));
        }
        return categoryEntity;

    }
    public static List<CategoryDTO> getCategoryDTOS(List<CategoryEntity> categoryEntities) {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryEntities.forEach(categoryEntity -> {
            categoryDTOS.add(CategoryTransformer.getCategoryDTO(categoryEntity));
        });
        return categoryDTOS;
    }
}
