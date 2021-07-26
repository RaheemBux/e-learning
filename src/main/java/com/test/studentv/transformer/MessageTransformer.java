package com.test.studentv.transformer;

import com.test.studentv.dto.MessageDTO;
import com.test.studentv.entity.MessageEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageTransformer {

    public static MessageDTO getMessageDTO(MessageEntity messageEntity){
        MessageDTO messageDTO = new MessageDTO();

        if(messageEntity.getId()!=null){
            messageDTO.setId(messageEntity.getId().toString());
        }
        if(messageEntity.getMessage()!=null){
            messageDTO.setMessage(messageEntity.getMessage());
        }
        if(messageEntity.getSenderEntity()!=null){
            messageDTO.setSenderDTO(UserTransformer.getUserDTO(messageEntity.getSenderEntity()));
        }
        if(messageEntity.getReceiverEntity()!=null){
            messageDTO.setReceiverDTO(UserTransformer.getUserDTO(messageEntity.getReceiverEntity()));
        }
        if(messageEntity.getVideoEntity()!=null){
            messageDTO.setVideoDTO(VideoTransformer.getVideoDTO(messageEntity.getVideoEntity()));
        }
        // five columns
        if(messageEntity.getCreatedBy()!=null){
            messageDTO.setCreatedBy(messageEntity.getCreatedBy().toString());
        }
        if(messageEntity.getModifiedBy()!=null){
            messageDTO.setModifiedBy(messageEntity.getModifiedBy().toString());
        }
        if(messageEntity.getCreatedDate()!=null){
            messageDTO.setCreatedDate(messageEntity.getCreatedDate().toString());
        }
        if(messageEntity.getModifiedDate()!=null){
            messageDTO.setModifiedDate(messageEntity.getModifiedDate().toString());
        }
        if(messageEntity.getStatus()!=null){
            messageDTO.setStatus(messageEntity.getStatus().toString());
        }
        return messageDTO;
    }
    public static MessageEntity getMessageEntity(MessageDTO messageDTO){
        MessageEntity messageEntity = new MessageEntity();

        if(messageDTO.getId()!=null){
            messageEntity.setId(Long.parseLong(messageDTO.getId()));
        }
        if(messageDTO.getMessage()!=null){
            messageEntity.setMessage(messageDTO.getMessage());
        }
        if(messageDTO.getSenderDTO()!=null){
            messageEntity.setSenderEntity(UserTransformer.getUserEntity(messageDTO.getSenderDTO()));
        }
        if(messageDTO.getReceiverDTO()!=null){
            messageEntity.setReceiverEntity(UserTransformer.getUserEntity(messageDTO.getReceiverDTO()));
        }
        if(messageDTO.getVideoDTO()!=null){
            messageEntity.setVideoEntity(VideoTransformer.getVideoEntity(messageDTO.getVideoDTO()));
        }
        // five columns
        if(messageDTO.getCreatedBy()!=null){
            messageEntity.setCreatedBy(Long.parseLong(messageDTO.getCreatedBy()));
        }
        if(messageDTO.getModifiedBy()!=null){
            messageEntity.setModifiedBy(Long.parseLong(messageDTO.getModifiedBy()));
        }
        messageEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        messageEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(messageDTO.getStatus()!=null){
            messageEntity.setStatus(Boolean.parseBoolean(messageDTO.getStatus()));
        }

        return messageEntity;
    }
    public static List<MessageDTO> getMessageDTOs(List<MessageEntity> messageEntities) {
        List<MessageDTO> messageDTOS = new ArrayList<>();
        messageEntities.forEach(messageEntity-> {
            messageDTOS.add(getMessageDTO(messageEntity));
        });
        return messageDTOS;
    }
}
