package com.main.app.service;

import java.util.List;

import com.main.app.model.MessageModel;
import com.main.app.repository.MessageRepository;
import com.main.app.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageDTO> initialMessageLoad(String loggedUser) {
        return messageRepository.initialMessageLoad(loggedUser);
    }

    @Override
    public void sendMessage(MessageDTO message) {
        messageRepository.sendMessage(message.getSender(), message.getReceiver(), message.getContent());
    }
}
