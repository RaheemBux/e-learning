package com.test.studentv.serviceImpl;

import com.test.studentv.entity.LikedCategoryEntity;
import com.test.studentv.repository.LikedCategoryRepository;
import com.test.studentv.service.LikedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedCategoryServiceImpl implements LikedCategoryService {

    @Autowired
    private LikedCategoryRepository likedCategoryRepository;

    @Override
    public List<LikedCategoryEntity> findAll() {
        return likedCategoryRepository.findAll();
    }

    @Override
    public LikedCategoryEntity create(LikedCategoryEntity likedCategoryEntity) {
        return likedCategoryRepository.save(likedCategoryEntity);
    }

    @Override
    public LikedCategoryEntity delete(LikedCategoryEntity likedCategoryEntity) {
        if (likedCategoryEntity.getId() != null) {
            likedCategoryEntity.setStatus(false);
            likedCategoryRepository.save(likedCategoryEntity);
            return likedCategoryEntity;
        }
        return null;
    }

    @Override
    public LikedCategoryEntity update(LikedCategoryEntity likedCategoryEntity) {
        if (likedCategoryEntity.getId() != null) {
            LikedCategoryEntity persisted = findById(likedCategoryEntity.getId());
            if (persisted == null) {
                return null;
            }
            LikedCategoryEntity updated = likedCategoryRepository.save(likedCategoryEntity);
            return updated;
        }
        return null;
    }

    @Override
    public LikedCategoryEntity findById(Long id) {
        Optional<LikedCategoryEntity> optionalUser = likedCategoryRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<LikedCategoryEntity> findAllByFilterWithPaging(Specification<LikedCategoryEntity> specification, Pageable pageable) {
        return likedCategoryRepository.findAll(specification,pageable);
    }

    @Override
    public List<LikedCategoryEntity> findAllByUserEntityId(Long id) {
        return likedCategoryRepository.findAllByUserEntityId(id);
    }
}
