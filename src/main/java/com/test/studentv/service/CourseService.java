package com.test.studentv.service;

import com.test.studentv.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourseService {
    List<CourseEntity> findAll();
    CourseEntity create(CourseEntity courseEntity) ;
    CourseEntity delete(CourseEntity courseEntity);
    CourseEntity update(CourseEntity courseEntity);
    CourseEntity findById(Long id);
    Page<CourseEntity> findAllByFilterWithPaging(Specification<CourseEntity> specification, Pageable pageable);
}
