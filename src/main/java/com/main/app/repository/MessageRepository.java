package com.main.app.repository;

import java.util.List;

import com.main.app.model.MessageModel;
import com.main.app.service.dto.MessageDTO;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageModel, Long> {
    @Query("SELECT * FROM MESSAGES WHERE SENDER = :loggedUser OR RECEIVER = :loggedUser") List<MessageDTO> initialMessageLoad(@Param("loggedUser") String loggedUser);

    @Modifying
    @Query("INSERT INTO MESSAGES (SENDER,RECEIVER,CONTENT) VALUES(:sender, :receiver, :content)")
    void sendMessage(@Param("sender") String sender, @Param("receiver") String receiver, @Param("content") String content);
}
