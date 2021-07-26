package com.test.studentv.service;

import com.test.studentv.entity.WatcherEntity;

import java.util.List;

public interface WatcherService {
    List<WatcherEntity> findAll();
    WatcherEntity create(WatcherEntity watcherEntity) ;
    WatcherEntity delete(WatcherEntity watcherEntity);
    WatcherEntity update(WatcherEntity watcherEntity);
    WatcherEntity findById(Long id);
}
