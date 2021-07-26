package com.test.studentv.repository;

import com.test.studentv.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long>, JpaSpecificationExecutor<CommentsEntity> {

    List<CommentsEntity> findAllByCourseEntityId(Long id);
}
