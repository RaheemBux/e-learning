package com.test.studentv.serviceImpl;

import com.test.studentv.entity.WatcherEntity;
import com.test.studentv.repository.WatcherRepository;
import com.test.studentv.service.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatcherServiceImpl implements WatcherService {

    @Autowired
    private WatcherRepository watcherRepository;

    @Override
    public List<WatcherEntity> findAll() {
        return watcherRepository.findAll();
    }

    @Override
    public WatcherEntity create(WatcherEntity watcherEntity) {
        return watcherRepository.save(watcherEntity);
    }

    @Override
    public WatcherEntity delete(WatcherEntity watcherEntity) {
        if (watcherEntity.getId() != null) {
            watcherEntity.setStatus(false);
            watcherRepository.save(watcherEntity);
            return watcherEntity;
        }
        return null;
    }

    @Override
    public WatcherEntity update(WatcherEntity watcherEntity) {
        if (watcherEntity.getId() != null) {
            WatcherEntity persisted = findById(watcherEntity.getId());
            if (persisted == null) {
                return null;
            }
            WatcherEntity updated = watcherRepository.save(watcherEntity);
            return updated;
        }
        return null;
    }

    @Override
    public WatcherEntity findById(Long id) {
        Optional<WatcherEntity> optionalUser = watcherRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
