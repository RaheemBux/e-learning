package com.test.studentv.service;

import com.test.studentv.entity.VideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface VideoService {
    List<VideoEntity> findAll();
    VideoEntity create(VideoEntity videoEntity) ;
    VideoEntity delete(VideoEntity videoEntity);
    VideoEntity update(VideoEntity videoEntity);
    VideoEntity findById(Long id);
    Page<VideoEntity> findAllByFilterWithPaging(Specification<VideoEntity> specification, Pageable pageable);
}
