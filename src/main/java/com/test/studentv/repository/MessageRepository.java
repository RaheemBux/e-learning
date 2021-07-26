package com.test.studentv.repository;

import com.test.studentv.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>, JpaSpecificationExecutor<MessageEntity> {

    List<MessageEntity> findAllBySenderEntityId(Long id);
    List<MessageEntity> findAllByReceiverEntityId(Long id);
    MessageEntity findByReceiverEntityId(Long id);
}
