package com.test.studentv.repository;

import com.test.studentv.entity.LikedHashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikedHashTagRepository extends JpaRepository<LikedHashTagEntity, Long>, JpaSpecificationExecutor<LikedHashTagEntity> {
    List<LikedHashTagEntity> findAllByUserEntityId(Long id);
}
