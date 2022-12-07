package com.example.piinfo.service;


import com.example.piinfo.model.Reminder;
import com.example.piinfo.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    // Listado de cuentas
    public List<Reminder> getAll(){
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Reminder save(Reminder entity){
        Reminder newReminder = reminderRepository.save(entity);
        return newReminder;
    }

    // Obtener cuenta
    public Reminder get(String id){
        Optional<Reminder> obj = reminderRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}