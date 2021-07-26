package com.test.studentv.serviceImpl;

import com.test.studentv.entity.CommentsEntity;
import com.test.studentv.repository.CommentsRepository;
import com.test.studentv.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<CommentsEntity> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public CommentsEntity create(CommentsEntity commentsEntity) {
        return commentsRepository.save(commentsEntity);
    }

    @Override
    public CommentsEntity delete(CommentsEntity commentsEntity) {
        if (commentsEntity.getId() != null) {
            commentsEntity.setStatus(false);
            commentsRepository.save(commentsEntity);
            return commentsEntity;
        }
        return null;
    }

    @Override
    public CommentsEntity update(CommentsEntity commentsEntity) {
        if (commentsEntity.getId() != null) {
            CommentsEntity persisted = findById(commentsEntity.getId());
            if (persisted == null) {
                return null;
            }
            CommentsEntity updated = commentsRepository.save(commentsEntity);
            return updated;
        }
        return null;
    }

    @Override
    public CommentsEntity findById(Long id) {
        Optional<CommentsEntity> optional = commentsRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<CommentsEntity> findAllByCourseEntityId(Long id) {
        return commentsRepository.findAllByCourseEntityId(id);
    }

    @Override
    public Page<CommentsEntity> findAllByFilterWithPaging(Specification<CommentsEntity> specification, Pageable pageable) {
        return commentsRepository.findAll(specification,pageable);
    }
}
