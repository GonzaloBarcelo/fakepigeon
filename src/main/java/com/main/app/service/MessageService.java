package com.main.app.service;

import java.util.List;
import com.main.app.service.dto.*;
import org.springframework.stereotype.Component;

@Component
public interface MessageService {
    public List<MessageDTO> initialMessageLoad(String username);
    public void sendMessage(MessageDTO message);
}
