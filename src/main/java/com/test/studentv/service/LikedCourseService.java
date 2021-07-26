package com.test.studentv.service;

import com.test.studentv.entity.LikedCourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface LikedCourseService {
    List<LikedCourseEntity> findAll();
    LikedCourseEntity create(LikedCourseEntity likedCourseEntity) ;
    LikedCourseEntity delete(LikedCourseEntity likedCourseEntity);
    LikedCourseEntity update(LikedCourseEntity likedCourseEntity);
    LikedCourseEntity findById(Long id);
    Page<LikedCourseEntity> findAllByFilterWithPaging(Specification<LikedCourseEntity> specification, Pageable pageable);
    List<LikedCourseEntity> findAllByUserEntityId(Long id);
}
