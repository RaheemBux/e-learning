package com.test.studentv.service;

import com.test.studentv.entity.HashTagEntity;

import java.util.List;

public interface HashTagService {
    List<HashTagEntity> findAll();
    HashTagEntity create(HashTagEntity hashTagEntity) ;
    HashTagEntity delete(HashTagEntity hashTagEntity);
    HashTagEntity update(HashTagEntity hashTagEntity);
    HashTagEntity findById(Long id);
}
