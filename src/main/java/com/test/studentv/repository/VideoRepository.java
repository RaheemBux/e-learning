package com.test.studentv.repository;

import com.test.studentv.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VideoRepository extends JpaRepository<VideoEntity, Long>, JpaSpecificationExecutor<VideoEntity> {
}
