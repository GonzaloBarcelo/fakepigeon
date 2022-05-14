package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.model.GlobalMessageModel.MessageType;
import com.example.demo.services.GlobalChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatControllerGlobal {

    @Autowired
    private GlobalChatService globalChatService;
    
    @MessageMapping("/chat.initialLoad")
    @SendTo("/topic/public")
    public GlobalMessageModel initialMessageLoad() {
        return globalChatService.initialMessageLoad().get(0);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public GlobalMessageModel sendMessage(@Payload GlobalMessageModel chatMessageModel) {
        globalChatService.storeMessage(chatMessageModel);
        return chatMessageModel;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public GlobalMessageModel addUser(@Payload GlobalMessageModel chatMessageModel, SimpMessageHeaderAccessor headerAccessor) {
        globalChatService.storeMessage(chatMessageModel);
        headerAccessor.getSessionAttributes().put("username", chatMessageModel.getSender());
        return chatMessageModel;
    }

}