package com.example.piinfo.service;

import com.example.piinfo.model.Conversation;
import com.example.piinfo.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
    }

    // Listado de cuentas
    public List<Conversation> getAll(){
        List<Conversation> conversations = conversationRepository.findAll();
        return conversations;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Conversation save(Conversation entity){
        Conversation newConversation = conversationRepository.save(entity);
        return newConversation;
    }

    // Obtener cuenta
    public Conversation get(String id){
        Optional<Conversation> obj = conversationRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}