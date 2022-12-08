package com.example.piinfo.service;

import com.example.piinfo.model.Alert;
import com.example.piinfo.model.Movement;
import com.example.piinfo.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    // Obtener cuenta
    public Alert get(String id){
        Optional<Alert> obj = alertRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Alert save(String alert_description){

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

        Alert alert = new Alert(alert_description, date, time);
        Alert newAlert = alertRepository.save(alert);

        return newAlert;
    }

    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }

}