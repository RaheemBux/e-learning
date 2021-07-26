package com.test.studentv.serviceImpl;

import com.test.studentv.entity.HashTagEntity;
import com.test.studentv.repository.HashTagRepository;
import com.test.studentv.service.HashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HashTagServiceImpl implements HashTagService {

    @Autowired
    private HashTagRepository hashTagRepository;

    @Override
    public List<HashTagEntity> findAll() {
        return hashTagRepository.findAll();
    }

    @Override
    public HashTagEntity create(HashTagEntity hashTagEntity) {
        return hashTagRepository.save(hashTagEntity);
    }

    @Override
    public HashTagEntity delete(HashTagEntity hashTagEntity) {
        if (hashTagEntity.getId() != null) {
            hashTagEntity.setStatus(false);
            hashTagRepository.save(hashTagEntity);
            return hashTagEntity;
        }
        return null;
    }

    @Override
    public HashTagEntity update(HashTagEntity hashTagEntity) {
        if (hashTagEntity.getId() != null) {
            HashTagEntity persisted = findById(hashTagEntity.getId());
            if (persisted == null) {
                return null;
            }
            HashTagEntity updated = hashTagRepository.save(hashTagEntity);
            return updated;
        }
        return null;
    }

    @Override
    public HashTagEntity findById(Long id) {
        Optional<HashTagEntity> optionalUser = hashTagRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
