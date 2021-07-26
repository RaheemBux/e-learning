package com.test.studentv.repository;

import com.test.studentv.entity.WatcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WatcherRepository extends JpaRepository<WatcherEntity, Long>, JpaSpecificationExecutor<WatcherEntity> {
}
