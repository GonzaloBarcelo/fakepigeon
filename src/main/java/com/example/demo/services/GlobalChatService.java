package com.example.demo.services;

import java.util.List;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.repository.GlobalChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalChatService {

    @Autowired
    private GlobalChatRepository globalChatRepository;

    public List<GlobalMessageModel> initialMessageLoad() {
        return globalChatRepository.initialMessageLoad();
    }
    
    public void storeMessage(GlobalMessageModel message) {
        globalChatRepository.storeMessage(message.getMessageType(), message.getSender(), message.getContent());
    }


}