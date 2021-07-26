package com.test.studentv.serviceImpl;

import com.test.studentv.entity.CourseEntity;
import com.test.studentv.repository.CourseRepository;
import com.test.studentv.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity create(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    @Override
    public CourseEntity delete(CourseEntity courseEntity) {
        if (courseEntity.getId() != null) {
            courseEntity.setStatus(false);
            courseRepository.save(courseEntity);
            return courseEntity;
        }
        return null;
    }

    @Override
    public CourseEntity update(CourseEntity courseEntity) {
        if (courseEntity.getId() != null) {
            CourseEntity persisted = findById(courseEntity.getId());
            if (persisted == null) {
                return null;
            }
            CourseEntity updated = courseRepository.save(courseEntity);
            return updated;
        }
        return null;
    }

    @Override
    public CourseEntity findById(Long id) {
        Optional<CourseEntity> optionalUser = courseRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<CourseEntity> findAllByFilterWithPaging(Specification<CourseEntity> specification, Pageable pageable) {
        return courseRepository.findAll(specification,pageable);
    }
}
