package com.test.studentv.service;

import com.test.studentv.entity.LikedCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface LikedCategoryService {
    List<LikedCategoryEntity> findAll();
    LikedCategoryEntity create(LikedCategoryEntity likedCategoryEntity) ;
    LikedCategoryEntity delete(LikedCategoryEntity likedCategoryEntity);
    LikedCategoryEntity update(LikedCategoryEntity likedCategoryEntity);
    LikedCategoryEntity findById(Long id);
    Page<LikedCategoryEntity> findAllByFilterWithPaging(Specification<LikedCategoryEntity> specification, Pageable pageable);
    List<LikedCategoryEntity> findAllByUserEntityId(Long id);
}
