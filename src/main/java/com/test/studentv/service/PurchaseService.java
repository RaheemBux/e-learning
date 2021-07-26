package com.test.studentv.service;

import com.test.studentv.entity.PurchaseEntity;

import java.util.List;

public interface PurchaseService {

    List<PurchaseEntity> findAll();
    PurchaseEntity create(PurchaseEntity purchaseEntity) ;
    PurchaseEntity delete(PurchaseEntity purchaseEntity);
    PurchaseEntity update(PurchaseEntity purchaseEntity);
    PurchaseEntity findById(Long id);
    List<PurchaseEntity> findAllByUserEntityId(Long id);
}
