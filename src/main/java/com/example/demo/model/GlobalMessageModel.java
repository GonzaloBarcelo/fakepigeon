package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("GLOBAL_MESSAGES")
public class GlobalMessageModel implements Serializable {

    private @Column("GLOBAL_MESSAGE_ID") @Getter @Setter @Id @Generated Long globalMessageId;
    private @Column("MESSAGE_TYPE") @Getter @Setter MessageType messageType;
    private @Column("SENDER") @Getter @Setter String sender;
    private @Column("CONTENT") @Getter @Setter String content;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getMessageType() {
        return this.messageType;
    }
}