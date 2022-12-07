package com.example.piinfo.service;

import com.example.piinfo.model.Alert;
import com.example.piinfo.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository){
        this.alertRepository = alertRepository;
    }

    // Listado de cuentas
    public List<Alert> getAll(){
        List<Alert> alerts = alertRepository.findAll();
        return alerts;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Alert save(Alert entity){
        Alert newAlert = alertRepository.save(entity);
        return newAlert;
    }

    // Obtener cuenta
    public Alert get(String id){
        Optional<Alert> obj = alertRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}