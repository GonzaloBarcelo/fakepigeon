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
@Table("PRIVATE_MESSAGES")
public class PrivateMessageModel implements Serializable {
    private @Column("PRIVATE_MESSAGE_ID") @Getter @Setter @Id @Generated Long privateMessageId;
    private @Column("SENDER") @Getter @Setter String sender;
    private @Column("RECEIVER") @Getter @Setter String receiver;
    private @Column("CONTENT") @Getter @Setter String content;

}
