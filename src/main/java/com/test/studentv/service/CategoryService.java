package com.test.studentv.service;

import com.test.studentv.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    CategoryEntity create(CategoryEntity categoryEntity) ;
    CategoryEntity delete(CategoryEntity categoryEntity);
    CategoryEntity update(CategoryEntity categoryEntity);
    CategoryEntity findById(Long id);
    Page<CategoryEntity> findAllByFilterWithPaging(Specification<CategoryEntity> specification, Pageable pageable);
}
