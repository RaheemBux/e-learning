package com.test.studentv.service;

import com.test.studentv.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MessageService {
    List<MessageEntity> findAll();
    MessageEntity create(MessageEntity messageEntity) ;
    MessageEntity delete(MessageEntity messageEntity);
    MessageEntity update(MessageEntity messageEntity);
    MessageEntity findById(Long id);
    Page<MessageEntity> findAllByFilterWithPaging(Specification<MessageEntity> specification, Pageable pageable);
    List<MessageEntity> findAllBySenderEntityId(Long id);
    List<MessageEntity> findAllByReceiverEntityId(Long id);
    MessageEntity findByReceiverEntityId(Long id);
}
