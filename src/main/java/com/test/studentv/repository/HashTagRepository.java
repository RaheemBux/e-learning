package com.test.studentv.repository;

import com.test.studentv.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long>, JpaSpecificationExecutor<HashTagEntity> {
}
