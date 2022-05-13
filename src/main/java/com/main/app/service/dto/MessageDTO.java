package com.main.app.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {
    private @Getter @Setter Long messageId;
    private @Getter @Setter String sender;
    private @Getter @Setter String receiver;
    private @Getter @Setter String content;

    public MessageDTO(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
