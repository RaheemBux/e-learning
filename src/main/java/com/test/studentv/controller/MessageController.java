package com.test.studentv.controller;

import com.test.studentv.dto.MessageDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.dto.UserDTO;
import com.test.studentv.entity.MessageEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.entity.VideoEntity;
import com.test.studentv.service.MessageService;
import com.test.studentv.service.UserService;
import com.test.studentv.service.VideoService;
import com.test.studentv.transformer.ClassTransformer;
import com.test.studentv.transformer.MessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<StatusDTO> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {

            if(messageDTO.getSenderDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(messageDTO.getSenderDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Sender ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(messageDTO.getReceiverDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(messageDTO.getReceiverDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Receiver ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(messageDTO.getVideoDTO() != null) {
                VideoEntity videoEntity = videoService.findById(Long.parseLong(messageDTO.getVideoDTO().getId()));
                if (videoEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Video ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            MessageEntity messageEntity = MessageTransformer.getMessageEntity(messageDTO);
            messageEntity.setStatus(true);
            messageService.create(messageEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Message Sent Successfully ",MessageTransformer.getMessageDTO(messageEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/receiveMessage/{receiverId}")
    public ResponseEntity<MessageDTO> receiveMessageByReceiverId(@PathVariable Long receiverId)  {
        MessageEntity messageEntity;
        MessageDTO messageDTO = null;
        try {
            messageEntity = messageService.findByReceiverEntityId(receiverId);
            if (messageEntity != null) {
                messageDTO = MessageTransformer.getMessageDTO(messageEntity);
                return new ResponseEntity<>(messageDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Message not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            MessageEntity messageEntity = messageService.findById(id);

            if (messageEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Message not found!"), HttpStatus.NOT_FOUND);
            } else {
                messageService.delete(messageEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Message deleted Successfully"), HttpStatus.OK);
    }
    @GetMapping(value = "/getAllMessagesBySendId/{senderId}")
    public List<MessageDTO> getAllMessagesBySendId(@PathVariable  Long senderId) {
        List<MessageEntity> messageEntities =messageService.findAllBySenderEntityId(senderId);
        return MessageTransformer.getMessageDTOs(messageEntities);
    }
    @GetMapping(value = "/getAllMessagesByReceiverId/{receiverId}")
    public List<MessageDTO> getAllMessagesByReceiverId(@PathVariable  Long receiverId) {
        List<MessageEntity> messageEntities =messageService.findAllByReceiverEntityId(receiverId);
        return MessageTransformer.getMessageDTOs(messageEntities);
    }
}
