package com.test.studentv.serviceImpl;

import com.test.studentv.entity.LikedHashTagEntity;
import com.test.studentv.repository.LikedHashTagRepository;
import com.test.studentv.service.LikedHashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedHashTagServiceImpl implements LikedHashTagService {

    @Autowired
    private LikedHashTagRepository likedHashTagRepository;

    @Override
    public List<LikedHashTagEntity> findAll() {
        return likedHashTagRepository.findAll();
    }

    @Override
    public LikedHashTagEntity create(LikedHashTagEntity likedHashTagEntity) {
        return likedHashTagRepository.save(likedHashTagEntity);
    }

    @Override
    public LikedHashTagEntity delete(LikedHashTagEntity likedHashTagEntity) {
        if (likedHashTagEntity.getId() != null) {
            likedHashTagEntity.setStatus(false);
            likedHashTagRepository.save(likedHashTagEntity);
            return likedHashTagEntity;
        }
        return null;
    }

    @Override
    public LikedHashTagEntity update(LikedHashTagEntity likedHashTagEntity) {
        if (likedHashTagEntity.getId() != null) {
            LikedHashTagEntity persisted = findById(likedHashTagEntity.getId());
            if (persisted == null) {
                return null;
            }
            LikedHashTagEntity updated = likedHashTagRepository.save(likedHashTagEntity);
            return updated;
        }
        return null;
    }

    @Override
    public LikedHashTagEntity findById(Long id) {
        Optional<LikedHashTagEntity> optionalUser = likedHashTagRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<LikedHashTagEntity> findAllByFilterWithPaging(Specification<LikedHashTagEntity> specification, Pageable pageable) {
        return likedHashTagRepository.findAll(specification,pageable);
    }

    @Override
    public List<LikedHashTagEntity> findAllByUserEntityId(Long id) {
        return likedHashTagRepository.findAllByUserEntityId(id);
    }
}
