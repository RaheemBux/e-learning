package com.test.studentv.service;

import com.test.studentv.entity.CommentsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommentsService {
    List<CommentsEntity> findAll();
    CommentsEntity create(CommentsEntity commentsEntity) ;
    CommentsEntity delete(CommentsEntity commentsEntity);
    CommentsEntity update(CommentsEntity commentsEntity);
    CommentsEntity findById(Long id);
    List<CommentsEntity> findAllByCourseEntityId(Long id);
    Page<CommentsEntity> findAllByFilterWithPaging(Specification<CommentsEntity> specification, Pageable pageable);
}
