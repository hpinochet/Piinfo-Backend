package com.example.piinfo.repository;

import com.example.piinfo.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
