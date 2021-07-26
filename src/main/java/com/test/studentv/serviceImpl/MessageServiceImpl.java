package com.test.studentv.serviceImpl;

import com.test.studentv.entity.MessageEntity;
import com.test.studentv.repository.MessageRepository;
import com.test.studentv.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public MessageEntity create(MessageEntity messageEntity) {
        return messageRepository.save(messageEntity);
    }

    @Override
    public MessageEntity delete(MessageEntity messageEntity) {
        if (messageEntity.getId() != null) {
            messageEntity.setStatus(false);
            messageRepository.save(messageEntity);
            return messageEntity;
        }
        return null;
    }

    @Override
    public MessageEntity update(MessageEntity messageEntity) {
        if (messageEntity.getId() != null) {
            MessageEntity persisted = findById(messageEntity.getId());
            if (persisted == null) {
                return null;
            }
            MessageEntity updated = messageRepository.save(messageEntity);
            return updated;
        }
        return null;
    }

    @Override
    public MessageEntity findById(Long id) {
        Optional<MessageEntity> optional = messageRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<MessageEntity> findAllByFilterWithPaging(Specification<MessageEntity> specification, Pageable pageable) {
        return messageRepository.findAll(specification,pageable);
    }

    @Override
    public List<MessageEntity> findAllBySenderEntityId(Long id) {
        return messageRepository.findAllBySenderEntityId(id);
    }

    @Override
    public List<MessageEntity> findAllByReceiverEntityId(Long id) {
        return messageRepository.findAllByReceiverEntityId(id);
    }

    @Override
    public MessageEntity findByReceiverEntityId(Long id) {
        return messageRepository.findByReceiverEntityId(id);
    }
}
