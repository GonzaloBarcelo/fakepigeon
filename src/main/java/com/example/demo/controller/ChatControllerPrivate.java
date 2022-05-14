package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.PrivateMessageModel;
import com.example.demo.services.PrivateChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatControllerPrivate {
    
    //TODO: PENSAR EN COMO HACER EL REFRESH DEL CHAT CUANDO SE ENVIA UN MENSAJE

    @Autowired
    private PrivateChatService privateChatService;

    /*
    *   INITIAL MESSAGE LOAD. CUANDO SE LOGEE EL USUARIO SE CARGARÁN SUS CONVERSACIONES
    */

    @PostMapping(path="/initialMessageLoad",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrivateMessageModel>> initialMessageLoad(@RequestBody String loggedUser) {
        return ResponseEntity.ok().body(privateChatService.initialMessageLoad(loggedUser));
    }

    /*
    *   LOAD MESSAGE INTO DATABASE. AL ENVIAR UN MENSAJE, SE GUARDA EN LA BASE DE DATOS
    */

    @PostMapping(path="/sendMessage",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void storeMessage(@RequestBody PrivateMessageModel message) {
        privateChatService.storeMessage(message);
    }
}