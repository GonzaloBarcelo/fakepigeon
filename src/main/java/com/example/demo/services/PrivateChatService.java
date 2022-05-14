package com.example.demo.services;

import java.util.List;

import com.example.demo.model.PrivateMessageModel;
import com.example.demo.repository.PrivateChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateChatService {
    @Autowired
    private PrivateChatRepository privateChatRepository;

    public List<PrivateMessageModel> initialMessageLoad(String loggedUser) {
        return privateChatRepository.initialMessageLoad(loggedUser);
    }

    public void storeMessage(PrivateMessageModel message) {
        privateChatRepository.storeMessage(message.getSender(), message.getReceiver(), message.getContent());
    }
}
