package com.main.app.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("MESSAGES")
public class MessageModel implements Serializable {
    private @Column("MESSAGE_ID") @Id @Generated Long messageId;
    private @Column("SENDER") String sender;
    private @Column("RECEIVER") String receiver;
    private @Column("CONTENT") String content;
}
