package com.test.studentv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO extends AbstractDTO{

    private String id;
    private String message;
    private UserDTO senderDTO;
    private UserDTO receiverDTO;
    private VideoDTO videoDTO;
}
