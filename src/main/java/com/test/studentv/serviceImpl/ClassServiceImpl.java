package com.test.studentv.serviceImpl;

import com.test.studentv.entity.ClassEntity;
import com.test.studentv.repository.ClassRepository;
import com.test.studentv.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<ClassEntity> findAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassEntity create(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public ClassEntity delete(ClassEntity classEntity) {
        if (classEntity.getId() != null) {
            classEntity.setStatus(false);
            classRepository.save(classEntity);
            return classEntity;
        }
        return null;
    }

    @Override
    public ClassEntity update(ClassEntity classEntity) {
        if (classEntity.getId() != null) {
            ClassEntity persisted = findById(classEntity.getId());
            if (persisted == null) {
                return null;
            }
            ClassEntity updated = classRepository.save(classEntity);
            return updated;
        }
        return null;
    }

    @Override
    public ClassEntity findById(Long id) {
        Optional<ClassEntity> optional = classRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<ClassEntity> findAllByFilterWithPaging(Specification<ClassEntity> specification, Pageable pageable) {
        return classRepository.findAll(specification, pageable);
    }


}
