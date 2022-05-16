package com.example.demo.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.model.PrivateMessageModel;
import com.example.demo.model.GlobalMessageModel.MessageType;
import com.example.demo.services.GlobalChatService;
import com.example.demo.services.PrivateChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    private GlobalChatService globalChatService;

    @Autowired
    private PrivateChatService privateChatService;
    
    @MessageMapping("/chat.initialLoad")
    public void initialGlobalMessageLoad() {
        List<GlobalMessageModel> initialMessageLoad = globalChatService.initialMessageLoad();
        Iterator<GlobalMessageModel> i = initialMessageLoad.iterator();

        while(i.hasNext()) {
            messagingTemplate.convertAndSend("/topic/public", i.next());
        }
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

    @MessageMapping("/chat.sendPrivateMessage")
    public void sendMessage(@Payload PrivateMessageModel chatMessageModel) {
        privateChatService.storeMessage(chatMessageModel);
        messagingTemplate.convertAndSend("/topic/" + chatMessageModel.getReceiver(), chatMessageModel);
        messagingTemplate.convertAndSend("/topic/" + chatMessageModel.getSender(), chatMessageModel);
    }

    @GetMapping("/Messages")
    public ResponseEntity<HashMap<Integer, ArrayList>> getMessages(){
        HashMap<Integer,ArrayList> status = new HashMap<>();
        List<GlobalMessageModel> all = globalChatService.getAllMessages();
        int i=0;
        for (GlobalMessageModel messageModel: all){
            if (messageModel.getContent().equals("$JOIN")){
            }
            else{
                i++;
                ArrayList<String> temp=new ArrayList<String>();
                temp.add(messageModel.getSender());
                temp.add(messageModel.getContent());
                status.put(i,temp);
            }
        }
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("/allMessages")
    public ResponseEntity<HashMap<Integer, ArrayList>> getAllMessages(){
        HashMap<Integer,ArrayList> status = new HashMap<>();
        List<GlobalMessageModel> all = globalChatService.getAllMessages();
        int i=0;
        for (GlobalMessageModel messageModel: all){
                i++;
                ArrayList<String> temp=new ArrayList<String>();
                temp.add(messageModel.getSender());
                temp.add(messageModel.getContent());
                status.put(i,temp);
        }
        return ResponseEntity.ok().body(status);
    }

}