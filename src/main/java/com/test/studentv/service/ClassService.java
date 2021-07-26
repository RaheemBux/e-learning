package com.test.studentv.service;

import com.test.studentv.entity.ClassEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ClassService {
    List<ClassEntity> findAll();
    ClassEntity create(ClassEntity classEntity) ;
    ClassEntity delete(ClassEntity classEntity);
    ClassEntity update(ClassEntity classEntity);
    ClassEntity findById(Long id);
    Page<ClassEntity> findAllByFilterWithPaging(Specification<ClassEntity> specification, Pageable pageable);
}
