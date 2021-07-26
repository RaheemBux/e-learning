package com.test.studentv.repository;

import com.test.studentv.entity.LikedCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikedCategoryRepository extends JpaRepository<LikedCategoryEntity, Long>, JpaSpecificationExecutor<LikedCategoryEntity> {
    List<LikedCategoryEntity> findAllByUserEntityId(Long id);
}
