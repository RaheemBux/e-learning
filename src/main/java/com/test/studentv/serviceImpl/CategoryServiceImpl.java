package com.test.studentv.serviceImpl;

import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.repository.CategoryRepository;
import com.test.studentv.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity delete(CategoryEntity categoryEntity) {
        if (categoryEntity.getId() != null) {
            categoryEntity.setStatus(false);
            categoryRepository.save(categoryEntity);
            return categoryEntity;
        }
        return null;
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        if (categoryEntity.getId() != null) {
            CategoryEntity persisted = findById(categoryEntity.getId());
            if (persisted == null) {
                return null;
            }
            CategoryEntity updated = categoryRepository.save(categoryEntity);
            return updated;
        }
        return null;
    }

    @Override
    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> optionalUser = categoryRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<CategoryEntity> findAllByFilterWithPaging(Specification<CategoryEntity> specification, Pageable pageable) {
        return categoryRepository.findAll(specification,pageable);
    }
}
