package com.example.demo.service;

import java.util.Iterator;
import java.util.List;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.model.PrivateMessageModel;
import com.example.demo.model.GlobalMessageModel.MessageType;
import com.example.demo.services.GlobalChatService;
import com.example.demo.services.PrivateChatService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ChatServiceTest {
    
    @Autowired
    private PrivateChatService privateChatService;

    @Autowired
    private GlobalChatService globalChatService;

    private PrivateMessageModel loggedUser = new PrivateMessageModel();

    private String test_sender = "ALICE";
    private String test_receiver = "BOB";

    @Test
    public void private_initial_message_load_retrieves_the_data_correctly_from_database() {
        loggedUser.setSender(test_sender);

        List<PrivateMessageModel> initialDataLoad = privateChatService.initialMessageLoad(loggedUser);
        Iterator<PrivateMessageModel> i = initialDataLoad.iterator();

        while(i.hasNext()) {
            PrivateMessageModel e = i.next();
            Assert.isTrue((e.getSender().equals(test_sender) || e.getSender().equals(test_receiver)),"the private user is not receiving the correct initial load of messages, mismatch of sender or receiver");
        }
    }

    @Test
    public void global_initial_message_load_retrieves_the_data_correctly_from_database() {
        List<GlobalMessageModel> initialDataLoad = globalChatService.initialMessageLoad();
        Iterator<GlobalMessageModel> i = initialDataLoad.iterator();

        while(i.hasNext()) {
            GlobalMessageModel e = i.next();
            Assert.isTrue(e.getMessageType() == MessageType.CHAT,"global messages are not loading correctly, expected MessageType CHAT");
        }
    }

    @Test
    public void private_messages_are_stored_correctly_in_the_database() {
        PrivateMessageModel test_message = new PrivateMessageModel();
        test_message.setSender(test_sender);
        test_message.setReceiver(test_receiver);
        test_message.setContent("TEST");

        privateChatService.storeMessage(test_message);

        loggedUser.setSender(test_sender);

        List<PrivateMessageModel> initialDataLoad = privateChatService.initialMessageLoad(loggedUser);
        Iterator<PrivateMessageModel> i = initialDataLoad.iterator();

        while(i.hasNext()) {
            PrivateMessageModel e = i.next();
            if ((e.getContent()).equals("TEST")) {
                Assert.isTrue(e.getSender().equals(test_sender) && e.getReceiver().equals(test_receiver),"private messages are not being stored correctly");
                break;
            }
        }
    }

    @Test
    public void global_messages_are_stored_correctly_in_the_database() {
        GlobalMessageModel test_message = new GlobalMessageModel();
        test_message.setMessageType(MessageType.CHAT);
        test_message.setSender(test_sender);
        test_message.setContent("TEST");

        globalChatService.storeMessage(test_message);

        List<GlobalMessageModel> initialDataLoad = globalChatService.initialMessageLoad();
        Iterator<GlobalMessageModel> i = initialDataLoad.iterator();

        while(i.hasNext()) {
            GlobalMessageModel e = i.next();
            if((e.getContent()).equals("TEST")) {
                Assert.isTrue(e.getMessageType() == MessageType.CHAT && e.getSender().equals(test_sender),"global messages are not being stored correctly");
            }
        }
    }
}
