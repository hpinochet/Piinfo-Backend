package com.example.piinfo.service;

import com.example.piinfo.model.Conversation;
import com.example.piinfo.model.Movement;
import com.example.piinfo.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    // Obtener cuenta
    public Conversation get(String id){
        Optional<Conversation> obj = conversationRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    public Conversation save(String message, String who){

        Calendar c1 = Calendar.getInstance();

        String actualDay = Integer.toString(c1.get(Calendar.DATE));
        String actualMonth = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String actualYear = Integer.toString(c1.get(Calendar.YEAR));

        String actualHour = Integer.toString(c1.get(Calendar.HOUR_OF_DAY));
        String actualMinute = Integer.toString(c1.get(Calendar.MINUTE));

        actualDay = dayTimeVerify(actualDay);
        actualMonth = dayTimeVerify(actualMonth);
        actualHour = dayTimeVerify(actualHour);
        actualMinute = dayTimeVerify(actualMinute);

        String date = actualDay + "/" + actualMonth + "/" + actualYear;
        String time = actualHour + ":" + actualMinute;

        Conversation conversation = new Conversation(date, time,who, message);
        Conversation newConversation = conversationRepository.save(conversation);

        return newConversation;
    }

    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }

}