package com.test.studentv.service;

import com.test.studentv.entity.LikedHashTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface LikedHashTagService {
    List<LikedHashTagEntity> findAll();
    LikedHashTagEntity create(LikedHashTagEntity likedHashTagEntity) ;
    LikedHashTagEntity delete(LikedHashTagEntity likedHashTagEntity);
    LikedHashTagEntity update(LikedHashTagEntity likedHashTagEntity);
    LikedHashTagEntity findById(Long id);
    Page<LikedHashTagEntity> findAllByFilterWithPaging(Specification<LikedHashTagEntity> specification, Pageable pageable);
    List<LikedHashTagEntity> findAllByUserEntityId(Long id);
}
