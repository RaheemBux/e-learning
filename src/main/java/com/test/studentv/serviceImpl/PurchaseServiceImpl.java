package com.test.studentv.serviceImpl;

import com.test.studentv.entity.PurchaseEntity;
import com.test.studentv.repository.PurchaseRepository;
import com.test.studentv.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<PurchaseEntity> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public PurchaseEntity create(PurchaseEntity purchaseEntity) {
        return purchaseRepository.save(purchaseEntity);
    }

    @Override
    public PurchaseEntity delete(PurchaseEntity purchaseEntity) {
        if (purchaseEntity.getId() != null) {
            purchaseEntity.setStatus(false);
            purchaseRepository.save(purchaseEntity);
            return purchaseEntity;
        }
        return null;
    }

    @Override
    public PurchaseEntity update(PurchaseEntity purchaseEntity) {
        if (purchaseEntity.getId() != null) {
            PurchaseEntity persisted = findById(purchaseEntity.getId());
            if (persisted == null) {
                return null;
            }
            PurchaseEntity updated = purchaseRepository.save(purchaseEntity);
            return updated;
        }

        return null;
    }

    @Override
    public PurchaseEntity findById(Long id) {
        Optional<PurchaseEntity> optional = purchaseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<PurchaseEntity> findAllByUserEntityId(Long id) {
        return purchaseRepository.findAllByUserEntityId(id);
    }
}
