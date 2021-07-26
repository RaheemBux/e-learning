package com.test.studentv.repository;

import com.test.studentv.entity.LikedCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LikedCourseRepository extends JpaRepository<LikedCourseEntity, Long>, JpaSpecificationExecutor<LikedCourseEntity> {
    List<LikedCourseEntity> findAllByUserEntityId(Long id);
}
