package com.main.app.controller;

import java.util.List;

import com.main.app.model.UserModel;
import com.main.app.service.MessageService;
import com.main.app.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    
    //TODO: PENSAR EN COMO HACER EL REFRESH DEL CHAT CUANDO SE ENVIA UN MENSAJE

    @Autowired
    private MessageService messageService;

    /*
    *   INITIAL MESSAGE LOAD. CUANDO SE LOGEE EL USUARIO SE CARGARÁN SUS CONVERSACIONES
    */

    @PostMapping(path="/initialMessageLoad",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageDTO>> initialMessageLoad(@RequestBody UserModel loggedUser) {
        return ResponseEntity.ok().body(messageService.initialMessageLoad(loggedUser.getUsername()));
    }

    /*
    *   LOAD MESSAGE INTO DATABASE. AL ENVIAR UN MENSAJE, SE GUARDA EN LA BASE DE DATOS
    */

    @PostMapping(path="/sendMessage",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO message) {
        messageService.sendMessage(message);
        return ResponseEntity.ok().body("{\"result\" : \"OK\"}");
    }
}

