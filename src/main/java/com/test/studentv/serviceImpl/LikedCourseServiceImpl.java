package com.test.studentv.serviceImpl;

import com.test.studentv.entity.LikedCourseEntity;
import com.test.studentv.repository.LikedCourseRepository;
import com.test.studentv.service.LikedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedCourseServiceImpl implements LikedCourseService {

    @Autowired
    private LikedCourseRepository likedCourseRepository;

    @Override
    public List<LikedCourseEntity> findAll() {
        return likedCourseRepository.findAll();
    }

    @Override
    public LikedCourseEntity create(LikedCourseEntity likedCourseEntity) {
        return likedCourseRepository.save(likedCourseEntity);
    }

    @Override
    public LikedCourseEntity delete(LikedCourseEntity likedCourseEntity) {
        if (likedCourseEntity.getId() != null) {
            likedCourseEntity.setStatus(false);
            likedCourseRepository.save(likedCourseEntity);
            return likedCourseEntity;
        }
        return null;
    }

    @Override
    public LikedCourseEntity update(LikedCourseEntity likedCourseEntity) {
        if (likedCourseEntity.getId() != null) {
            LikedCourseEntity persisted = findById(likedCourseEntity.getId());
            if (persisted == null) {
                return null;
            }
            LikedCourseEntity updated = likedCourseRepository.save(likedCourseEntity);
            return updated;
        }
        return null;
    }

    @Override
    public LikedCourseEntity findById(Long id) {
        Optional<LikedCourseEntity> optionalUser = likedCourseRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<LikedCourseEntity> findAllByFilterWithPaging(Specification<LikedCourseEntity> specification, Pageable pageable) {
        return likedCourseRepository.findAll(specification,pageable);
    }

    @Override
    public List<LikedCourseEntity> findAllByUserEntityId(Long id) {
        return likedCourseRepository.findAllByUserEntityId(id);
    }
}
