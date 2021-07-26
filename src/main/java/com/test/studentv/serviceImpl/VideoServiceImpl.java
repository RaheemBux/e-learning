package com.test.studentv.serviceImpl;

import com.test.studentv.entity.VideoEntity;
import com.test.studentv.repository.VideoRepository;
import com.test.studentv.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<VideoEntity> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public VideoEntity create(VideoEntity videoEntity) {
        return videoRepository.save(videoEntity);
    }

    @Override
    public VideoEntity delete(VideoEntity videoEntity) {
        if (videoEntity.getId() != null) {
            videoEntity.setStatus(false);
            videoRepository.save(videoEntity);
            return videoEntity;
        }
        return null;
    }

    @Override
    public VideoEntity update(VideoEntity videoEntity) {
        if (videoEntity.getId() != null) {
            VideoEntity persisted = findById(videoEntity.getId());
            if (persisted == null) {
                return null;
            }
            VideoEntity updated = videoRepository.save(videoEntity);
            return updated;
        }
        return null;
    }

    @Override
    public VideoEntity findById(Long id) {
        Optional<VideoEntity> optionalUser = videoRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Page<VideoEntity> findAllByFilterWithPaging(Specification<VideoEntity> specification, Pageable pageable) {
        return videoRepository.findAll(specification,pageable);
    }
}
