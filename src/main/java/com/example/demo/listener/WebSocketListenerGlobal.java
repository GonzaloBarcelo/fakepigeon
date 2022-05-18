package com.example.demo.listener;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.services.GlobalChatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListenerGlobal {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketListenerGlobal.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private GlobalChatService globalChatService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("New web socket connection established");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            logger.info("User Disconnected : " + username);

            GlobalMessageModel chatMessageGlobal = new GlobalMessageModel();
            chatMessageGlobal.setMessageType(GlobalMessageModel.MessageType.LEAVE);
            chatMessageGlobal.setSender(username);
            chatMessageGlobal.setContent("$LEAVE");

            globalChatService.storeMessage(chatMessageGlobal);

            messagingTemplate.convertAndSend("/topic/public", chatMessageGlobal);
        }
    }
}