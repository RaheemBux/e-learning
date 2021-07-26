package com.test.studentv.transformer;

import com.test.studentv.dto.PurchaseDTO;
import com.test.studentv.entity.PurchaseEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class PurchaseTransformer {

    public static PurchaseDTO getPurchaseDTO(PurchaseEntity purchaseEntity){
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        if(purchaseEntity.getId()!=null){
            purchaseDTO.setId(purchaseEntity.getId().toString());
        }
        if(purchaseEntity.getPurchaseDate()!=null){
            purchaseDTO.setPurchaseDate(purchaseEntity.getPurchaseDate().toString());
        }
        if(purchaseEntity.getPrice()!=null){
            purchaseDTO.setPrice(purchaseEntity.getPrice().toString());
        }
        if(purchaseEntity.getUserEntity()!=null){
            purchaseDTO.setUserDTO(UserTransformer.getUserDTO(purchaseEntity.getUserEntity()));
        }
        if(purchaseEntity.getCourseEntity()!=null){
            purchaseDTO.setCourseDTO(CourseTransformer.getCourseDTO(purchaseEntity.getCourseEntity()));
        }
        if(purchaseEntity.getCreatedBy()!=null){
            purchaseDTO.setCreatedBy(purchaseEntity.getCreatedBy().toString());
        }
        if(purchaseEntity.getModifiedBy()!=null){
            purchaseDTO.setModifiedBy(purchaseEntity.getModifiedBy().toString());
        }
        if(purchaseEntity.getCreatedDate()!=null){
            purchaseDTO.setCreatedDate(purchaseEntity.getCreatedDate().toString());
        }
        if(purchaseEntity.getModifiedDate()!=null){
            purchaseDTO.setModifiedDate(purchaseEntity.getModifiedDate().toString());
        }
        if(purchaseEntity.getStatus()!=null){
            purchaseDTO.setStatus(purchaseEntity.getStatus().toString());
        }

        return purchaseDTO;
    }
    public static PurchaseEntity getPurchaseEntity(PurchaseDTO purchaseDTO){
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        if(purchaseDTO.getId()!=null){
            purchaseEntity.setId(Long.parseLong(purchaseDTO.getId()));
        }
        if(purchaseDTO.getPurchaseDate()!=null){
            purchaseEntity.setPurchaseDate(CommonUtil.getCurrentTimestamp());
        }
        if(purchaseDTO.getPrice()!=null){
            purchaseEntity.setPrice(Double.parseDouble(purchaseDTO.getPrice()));
        }
        if(purchaseDTO.getUserDTO()!=null){
            purchaseEntity.setUserEntity(UserTransformer.getUserEntity(purchaseDTO.getUserDTO()));
        }
        if(purchaseDTO.getCourseDTO()!=null){
            purchaseEntity.setCourseEntity(CourseTransformer.getCourseEntity(purchaseDTO.getCourseDTO()));
        }
        if(purchaseDTO.getModifiedBy()!=null){
            purchaseEntity.setModifiedBy(Long.parseLong(purchaseDTO.getModifiedBy()));
        }
        purchaseEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        purchaseEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(purchaseDTO.getStatus()!=null){
            purchaseEntity.setStatus(Boolean.valueOf(purchaseDTO.getStatus()));
        }
        return purchaseEntity;
    }
    public static List<PurchaseDTO> getPurchaseDTOS(List<PurchaseEntity> purchaseEntities) {
        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();
        purchaseEntities.forEach(purchaseEntity -> {
            purchaseDTOS.add(PurchaseTransformer.getPurchaseDTO(purchaseEntity));
        });
        return purchaseDTOS;
    }
}
